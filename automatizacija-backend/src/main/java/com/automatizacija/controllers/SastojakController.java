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

import com.automatizacija.dtos.SastojakDto;
import com.automatizacija.models.Sastojak;
import com.automatizacija.services.SastojakService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class SastojakController {

	@Autowired
	private SastojakService sastojakService;
	
	@GetMapping("/sastojci")
	public ResponseEntity<List<Sastojak>> getAllSastojaks() {
		return new ResponseEntity<List<Sastojak>>(sastojakService.getAllSastojaks(), HttpStatus.OK);
	}
	
	@GetMapping("/sastojak/{sastojak_id}")
	public ResponseEntity<Sastojak> getSastojakById(@PathVariable long sastojak_id) {
		Sastojak sastojak = sastojakService.getSastojakById(sastojak_id);
		if(sastojak != null) {
			return new ResponseEntity<Sastojak>(sastojak, HttpStatus.OK);
		}
		return new ResponseEntity<Sastojak>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("createSastojak")
	public ResponseEntity<Sastojak> createSastojak(@RequestBody SastojakDto sastojakDto) {
		Sastojak sastojak = sastojakService.createSastojak(sastojakDto);
		if(sastojak != null) {
			return new ResponseEntity<Sastojak>(sastojak, HttpStatus.CREATED);
		}
		return new ResponseEntity<Sastojak>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/updateSastojak/{sastojak_id}")
	public ResponseEntity<Sastojak> updateSastojak(@PathVariable long sastojak_id, @RequestBody SastojakDto sastojakDto) {
		Sastojak sastojak = sastojakService.updateSastojak(sastojak_id, sastojakDto);
		if(sastojak != null) {
			return new ResponseEntity<Sastojak>(sastojak, HttpStatus.OK);
		}
		return new ResponseEntity<Sastojak>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/deleteSastojak/{sastojak_id}")
	public ResponseEntity<Sastojak> deleteSastojak(@PathVariable long sastojak_id) {
		Sastojak sastojak = sastojakService.deleteSastojak(sastojak_id);
		if (sastojak != null) {
			return new ResponseEntity<Sastojak>(sastojak, HttpStatus.OK);
		}
		return new ResponseEntity<Sastojak>(HttpStatus.BAD_REQUEST);
	}
}
