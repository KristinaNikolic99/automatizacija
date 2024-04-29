package com.automatizacija.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automatizacija.dtos.NarudzbinaDto;
import com.automatizacija.models.Korisnik;
import com.automatizacija.models.Narudzbina;
import com.automatizacija.models.Status;
import com.automatizacija.repositories.KorisnikRepository;
import com.automatizacija.repositories.NarudzbinaRepository;

@Service
public class NarudzbinaService {

	@Autowired
	private NarudzbinaRepository narudzbinaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	public List<Narudzbina> getAllNarudzbinas() {
		return narudzbinaRepository.findAll();
	}
	
	public Narudzbina getNarudzbinaById(long narudzbina_id) {
		return narudzbinaRepository.findById(narudzbina_id);
	}
	
	public Narudzbina createNarudzbina(NarudzbinaDto narudzbinaDto) {
		Narudzbina narudzbina = this.modelMapper.map(narudzbinaDto, Narudzbina.class);
		Korisnik korisnik = korisnikRepository.findById(narudzbinaDto.getKorisnik_id());
		narudzbina.setKorisnik(korisnik);
		return narudzbinaRepository.save(narudzbina);
	}
	
	public Narudzbina updateNarudzbina(long narudzbina_id, NarudzbinaDto narudzbinaDto) {
		Narudzbina narudzbina = narudzbinaRepository.findById(narudzbina_id);
		Korisnik korisnik = korisnikRepository.findById(narudzbinaDto.getKorisnik_id());
		narudzbina.setBrojNarudzbine(narudzbinaDto.getBrojNarudzbine());
		narudzbina.setDatumNarudzbine(narudzbinaDto.getDatumNarudzbine());
		narudzbina.setUkupnaCena(narudzbinaDto.getUkupnaCena());
		narudzbina.setKomentar(narudzbinaDto.getKomentar());
		narudzbina.setStatus(Status.valueOf(narudzbinaDto.getStatus()));
		narudzbina.setKorisnik(korisnik);
		return narudzbinaRepository.save(narudzbina);
	}
	
	public Narudzbina deleteNarudzbina(long narudzbina_id) {
		Narudzbina narudzbina = narudzbinaRepository.findById(narudzbina_id);
		if(narudzbina != null) {
			narudzbinaRepository.delete(narudzbina);
			return narudzbina;
		}
		return null;
	}
}
