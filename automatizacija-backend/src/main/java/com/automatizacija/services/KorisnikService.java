package com.automatizacija.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.automatizacija.dtos.KorisnikDto;
import com.automatizacija.models.Korisnik;
import com.automatizacija.models.TipKorisnika;
import com.automatizacija.repositories.KorisnikRepository;

@Service
public class KorisnikService {

	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<Korisnik> getAllKorisniks() {
		return korisnikRepository.findAll();
	}
	
	public Korisnik getKorisnikById(long korisnik_id) {
		return korisnikRepository.findById(korisnik_id);
	}
	
	public Korisnik register(KorisnikDto korisnikDto) {
		Korisnik korisnik = this.modelMapper.map(korisnikDto, Korisnik.class);
		korisnik.setPassword(passwordEncoder.encode(korisnik.getPassword()));
		korisnik.setTipKorisnika(TipKorisnika.Kupac);
		if (korisnikRepository.findByEmail(korisnik.getEmail()) != null) {
			return null;
		}
		return korisnikRepository.save(korisnik);
	}
	
	public Korisnik logIn(String email, String password) {
		Korisnik korisnik = korisnikRepository.findByEmail(email);
		if(!korisnik.isStatusNaloga()) {
			return null;
		}
		if(korisnik != null && passwordEncoder.matches(password, korisnik.getPassword())) {
			return korisnik;
		}
		return null;
	}
	
	public Korisnik updateKorisnik(long korisnik_id, KorisnikDto korisnikDto) {
		Korisnik korisnik = korisnikRepository.findById(korisnik_id);
		korisnik.setIme(korisnikDto.getIme());
		korisnik.setPrezime(korisnikDto.getPrezime());
		korisnik.setEmail(korisnikDto.getEmail());
		korisnik.setPassword(korisnikDto.getPassword());
		korisnik.setTipKorisnika(TipKorisnika.valueOf(korisnikDto.getTipKorisnika()));
		return korisnikRepository.save(korisnik);
	}
	
	public Korisnik deleteKorisnik(long korisnik_id) {
		Korisnik korisnik = korisnikRepository.findById(korisnik_id);
		if(korisnik != null) {
			korisnikRepository.delete(korisnik);
			return korisnik;
		}
		return null;
	}
}
