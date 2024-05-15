package com.automatizacija.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.automatizacija.dtos.ProcesProizvodnjeDto;
import com.automatizacija.models.ProcesProizvodnje;
import com.automatizacija.models.ProizvodnjaPoruka;
import com.automatizacija.services.ProcesProizvodnjeService;

@RestController
@RequestMapping("/api")
public class ProcesProizvodnjeController {

	@Autowired
	private ProcesProizvodnjeService procesProizvodnjeService; 
	
	@GetMapping("/procesi-proizvodnje")
	public ResponseEntity<List<ProcesProizvodnje>> getAllProcesProizvodnjas() {
		return new ResponseEntity<List<ProcesProizvodnje>>(procesProizvodnjeService.getAllProcesProizvodnjes(), HttpStatus.OK);
	}
	
	@GetMapping("/proces-proizvodnje/korisnik")
	public ResponseEntity<List<ProcesProizvodnje>> getAllKorisnik() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		List<ProcesProizvodnje> korisnik = procesProizvodnjeService.getAllKorisnik(email);
		if(korisnik != null) {
			return new ResponseEntity<List<ProcesProizvodnje>>(korisnik, HttpStatus.OK);
		}
		return new ResponseEntity<List<ProcesProizvodnje>>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/proces-proizvodnje")
	public ResponseEntity<ProizvodnjaPoruka> createProcesProizvodnje(@RequestBody ProcesProizvodnjeDto procesProizvodnjeDto) throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
        
		ProizvodnjaPoruka proizvodnjaPoruka = procesProizvodnjeService.createProcesProizvodnje(procesProizvodnjeDto, email);
		if(proizvodnjaPoruka.getStatus().equals(HttpStatus.BAD_REQUEST)) {
			return new ResponseEntity<ProizvodnjaPoruka>(proizvodnjaPoruka, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ProizvodnjaPoruka>(proizvodnjaPoruka, HttpStatus.OK);
	}
	
	@GetMapping("/proces-proizvodnje/zavrsi/{proces_proizvodnje_id}")
	public ResponseEntity<ProcesProizvodnje> zavrsiProcesProizvodnje(@PathVariable long proces_proizvodnje_id) {
		ProcesProizvodnje procesProizvodnje = procesProizvodnjeService.zavrsiProcesProizvodnje(proces_proizvodnje_id);
		if(procesProizvodnje != null) {
			return new ResponseEntity<ProcesProizvodnje>(procesProizvodnje, HttpStatus.OK);
		}
		return new ResponseEntity<ProcesProizvodnje>(HttpStatus.NOT_FOUND);
	}
}
