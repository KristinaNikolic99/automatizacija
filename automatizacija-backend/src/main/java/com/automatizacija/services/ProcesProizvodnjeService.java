package com.automatizacija.services;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.automatizacija.dtos.ProcesProizvodnjeDto;
import com.automatizacija.email.EmailService;
import com.automatizacija.models.DetaljiSastava;
import com.automatizacija.models.Korisnik;
import com.automatizacija.models.Masina;
import com.automatizacija.models.ProcesProizvodnje;
import com.automatizacija.models.Proizvodnja;
import com.automatizacija.models.ProizvodnjaPoruka;
import com.automatizacija.models.Sastojak;
import com.automatizacija.models.Sok;
import com.automatizacija.models.Zaliha;
import com.automatizacija.repositories.KorisnikRepository;
import com.automatizacija.repositories.MasinaRepository;
import com.automatizacija.repositories.ProcesProizvodnjeRepository;
import com.automatizacija.repositories.ProizvodnjaRepository;
import com.automatizacija.repositories.SokRepository;
import com.automatizacija.repositories.ZalihaRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

@Service
public class ProcesProizvodnjeService {
	
	@Autowired
	private ProcesProizvodnjeRepository procesProizvodnjeRepository;
	
	@Autowired
	private ProizvodnjaRepository proizvodnjaRepository;
	
	@Autowired
	private SokRepository sokRepository;
	
	@Autowired
	private MasinaRepository masinaRepository;
	
	@Autowired
	private ZalihaRepository zalihaRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	public List<ProcesProizvodnje> getAllProcesProizvodnjes() {
		return procesProizvodnjeRepository.findAll();
	}
	
	public List<ProcesProizvodnje> getAllKorisnik(String email) {
		Korisnik korisnik = korisnikRepository.findByEmail(email);
		return procesProizvodnjeRepository.findByKorisnik(korisnik);
	}
	
	public ProizvodnjaPoruka createProcesProizvodnje(ProcesProizvodnjeDto procesProizvodnjeDto, String email) throws Exception {
		Sok sok = sokRepository.findById(procesProizvodnjeDto.getSok_id());
		Masina masina = masinaRepository.findById(procesProizvodnjeDto.getMasina_id());
		Proizvodnja proizvodnja = proizvodnjaRepository.findBySokAndMasina(sok, masina);
		
		Map<Zaliha, Double> proizvodnjaMap = new HashMap<>();
		List<Zaliha> nedostajuceZalihe = new ArrayList<>();
		StringBuilder nedostajucePoruka = new StringBuilder();
		nedostajucePoruka.append("Zalihe koje nedostaju su: \n");
		for(DetaljiSastava detaljSastava : sok.getDetaljiSastava()) {
			Sastojak sastojak = detaljSastava.getSastojak();
			double kolicinaZaProizvodnju = detaljSastava.getKolicina()*procesProizvodnjeDto.getKolicina();
			Zaliha zaliha = zalihaRepository.findBySastojak(sastojak);
			
			proizvodnjaMap.put(zaliha, kolicinaZaProizvodnju);
			
			if(zaliha.getKolicina() < kolicinaZaProizvodnju) {
				nedostajuceZalihe.add(zaliha);
				nedostajucePoruka.append(zaliha.getSastojak().getNaziv() + "\n");
			}
		}
		
		if(!nedostajuceZalihe.isEmpty()) {
			return new ProizvodnjaPoruka(nedostajucePoruka.toString(), HttpStatus.BAD_REQUEST);
		}
		
		for(Map.Entry<Zaliha, Double> entry : proizvodnjaMap.entrySet()) {
			Zaliha zaliha = entry.getKey();
			Double kolicinaZaProizvodnju = entry.getValue();
			zaliha.setKolicina(zaliha.getKolicina() - kolicinaZaProizvodnju);
			zalihaRepository.save(zaliha);
		}
		
		Korisnik korisnik = korisnikRepository.findByEmail(email);
		ProcesProizvodnje procesProizvodnje = new ProcesProizvodnje();
		procesProizvodnje.setDatumPocetka(LocalDateTime.now());
		procesProizvodnje.setAktivna(true);
		procesProizvodnje.setProizvodnja(proizvodnja);
		procesProizvodnje.setKorisnik(korisnik);
		procesProizvodnjeRepository.save(procesProizvodnje);
		emailService.sendWithPdf(email, generatePdf(procesProizvodnje, procesProizvodnjeDto, masina));
		return new ProizvodnjaPoruka("Uspesno ste pokrenuli poizvodnju za sok: " + sok.getVrsta(), HttpStatus.OK);
	}
	
	private ByteArrayOutputStream generatePdf(ProcesProizvodnje procesProizvodnje, ProcesProizvodnjeDto procesProizvodnjeDto, Masina masina)
			throws Exception {
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();
	     try {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);

            document.open();
            
            Font titleFont = new Font(FontFamily.HELVETICA, 18, Font.BOLD);
            Font normalFont = new Font(FontFamily.HELVETICA, 12, Font.NORMAL);
            Font boldFont = new Font(FontFamily.HELVETICA, 12, Font.BOLD);
            Font italicFont = new Font(FontFamily.HELVETICA, 12, Font.ITALIC);
            Font blueFont = new Font(FontFamily.HELVETICA, 12, Font.NORMAL);
            blueFont.setColor(0, 0, 255);
            
            Paragraph title = new Paragraph("Proizvodnja", titleFont);
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("\n"));

            document.add(new Paragraph("Uspešno ste inicirali početak proizvodnje", boldFont));
            document.add(new Paragraph("Za sok pod nazivom: " + procesProizvodnje.getProizvodnja().getSok().getVrsta(), normalFont));
            document.add(new Paragraph("Opisan kao: " + procesProizvodnje.getProizvodnja().getSok().getOpis(), italicFont));
            document.add(new Paragraph("Količina: " + procesProizvodnjeDto.getKolicina() + " flaša od 1l", normalFont));
            document.add(new Paragraph("Na mašini: " + masina.getNaziv() + " sa serijskim brojem " + masina.getSerijskiBroj(), normalFont));
            document.add(new Paragraph("Vreme početka proizvodnje je: " + procesProizvodnje.getDatumPocetka().format(DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm")), blueFont));
         
            
            document.close();
	     } catch (DocumentException e) {
	            e.printStackTrace();
	     }
	     return baos;
	}
	
	public ProcesProizvodnje zavrsiProcesProizvodnje(long proces_proizvodnje_id) {
		ProcesProizvodnje procesProizvodnje = procesProizvodnjeRepository.findById(proces_proizvodnje_id);
		if(procesProizvodnje == null) {
			return null;
		}
		procesProizvodnje.setAktivna(false);
		return procesProizvodnjeRepository.save(procesProizvodnje);
	}

}
