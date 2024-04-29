package com.automatizacija.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.automatizacija.dtos.NarudzbinaDto;
import com.automatizacija.models.Narudzbina;
import com.automatizacija.services.NarudzbinaService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class NarudzbinaController {

	@Autowired
	private NarudzbinaService narudzbinaService;
	
	@GetMapping("/narudzbine")
	public ResponseEntity<List<Narudzbina>> getAllNarudzbinas() {
		return new ResponseEntity<List<Narudzbina>>(narudzbinaService.getAllNarudzbinas(), HttpStatus.OK);
	}
	
	@GetMapping("/narudzbina/{narudzbina_id}")
	public ResponseEntity<Narudzbina> getNarudzbinaById(@PathVariable long narudzbina_id) {
		Narudzbina narudzbina = narudzbinaService.getNarudzbinaById(narudzbina_id);
		if(narudzbina != null) {
			return new ResponseEntity<Narudzbina>(narudzbina, HttpStatus.OK);
		}
		return new ResponseEntity<Narudzbina>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/createNarudzbina")
	public ResponseEntity<Narudzbina> createNarudzbina(@RequestBody NarudzbinaDto narudzbinaDto) {
		Narudzbina narudzbina = narudzbinaService.createNarudzbina(narudzbinaDto);
		if(narudzbina != null) {
			return new ResponseEntity<Narudzbina>(narudzbina, HttpStatus.CREATED);
		}
		return new ResponseEntity<Narudzbina>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/updateNarudzbina/{narudzbina_id}")
	public ResponseEntity<Narudzbina> updateNarudzbina(@PathVariable long narudzbina_id, @RequestBody NarudzbinaDto narudzbinaDto) {
		Narudzbina narudzbina = narudzbinaService.updateNarudzbina(narudzbina_id, narudzbinaDto);
		if(narudzbina != null) {
			return new ResponseEntity<Narudzbina>(narudzbina, HttpStatus.OK);
		}
		return new ResponseEntity<Narudzbina>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/deleteNarudzbina/{narudzbina_id}")
	public ResponseEntity<Narudzbina> deleteNarudzbina(@ PathVariable long narudzbina_id) {
		Narudzbina narudzbina = narudzbinaService.deleteNarudzbina(narudzbina_id);
		if(narudzbina != null) {
			return new ResponseEntity<Narudzbina>(narudzbina, HttpStatus.OK);
		}
		return new ResponseEntity<Narudzbina>(HttpStatus.BAD_REQUEST);
	}
}
