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

import com.automatizacija.dtos.ProizvodnjaDto;
import com.automatizacija.models.Proizvodnja;
import com.automatizacija.services.ProizvodnjaService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProizvodnjaController {
	
	@Autowired
	private ProizvodnjaService proizvodnjaService;
	
	@GetMapping("/proizvodnje")
	public ResponseEntity<List<Proizvodnja>> getAllProizvodnjas() {
		return new ResponseEntity<List<Proizvodnja>>(proizvodnjaService.getAllProizvodnjas(), HttpStatus.OK);
	}
	
	@GetMapping("/proizvodnja/{proizvodnja_id}")
	public ResponseEntity<Proizvodnja> getProizvodnjaById(@PathVariable long proizvodnja_id) {
		Proizvodnja proizvodnja = proizvodnjaService.getProizvodnjaById(proizvodnja_id);
		if(proizvodnja != null) {
			return new ResponseEntity<Proizvodnja>(proizvodnja, HttpStatus.OK);
		}
		return new ResponseEntity<Proizvodnja>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/proizvodnjaSok/{sok_id}")
	public ResponseEntity<List<Proizvodnja>> getProizvodnjaBySokId(@PathVariable long sok_id) {
		List<Proizvodnja> proizvodnja =  proizvodnjaService.getProizvodnjaBySokId(sok_id);
		if(!proizvodnja.isEmpty()) {
			return new ResponseEntity<List<Proizvodnja>>(proizvodnja, HttpStatus.OK);
		}
		return new ResponseEntity<List<Proizvodnja>>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/createProizvodnja")
	public ResponseEntity<Proizvodnja> createProizvodnja(@RequestBody ProizvodnjaDto proizvodnjaDto) {
		Proizvodnja proizvodnja = proizvodnjaService.createProizvodnja(proizvodnjaDto);
		if(proizvodnja != null) {
			return new ResponseEntity<Proizvodnja>(proizvodnja, HttpStatus.CREATED);
		}
		return new ResponseEntity<Proizvodnja>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/updateProizvodnja/{proizvodnja_id}")
	public ResponseEntity<Proizvodnja> updateProizvodnja(@PathVariable long proizvodnja_id, @RequestBody ProizvodnjaDto proizvodnjaDto) {
		Proizvodnja proizvodnja = proizvodnjaService.updateProizvodnja(proizvodnja_id, proizvodnjaDto);
		if(proizvodnja != null) {
			return new ResponseEntity<Proizvodnja>(proizvodnja, HttpStatus.OK);
		}
		return new ResponseEntity<Proizvodnja>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/deleteProizvodnja/{proizvodnja_id}")
	public ResponseEntity<Proizvodnja> deleteProizvodnja(@PathVariable long proizvodnja_id) {
		Proizvodnja proizvodnja = proizvodnjaService.deleteProizvodnja(proizvodnja_id);
		if(proizvodnja != null) {
			return new ResponseEntity<Proizvodnja>(proizvodnja, HttpStatus.OK);
		}
		return new ResponseEntity<Proizvodnja>(HttpStatus.BAD_REQUEST);
	}

}
