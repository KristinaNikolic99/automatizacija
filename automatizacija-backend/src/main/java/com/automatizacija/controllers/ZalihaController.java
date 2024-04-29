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

import com.automatizacija.dtos.ZalihaDto;
import com.automatizacija.models.Zaliha;
import com.automatizacija.services.ZalihaService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ZalihaController {

	@Autowired
	private ZalihaService zalihaService;
	
	@GetMapping("/zalihe")
	public ResponseEntity<List<Zaliha>> getAllZalihas() {
		return new ResponseEntity<List<Zaliha>>(zalihaService.getAllZalihas(), HttpStatus.OK);
	}
	
	@GetMapping("/zaliha/{zaliha_id}")
	public ResponseEntity<Zaliha> getZalihaById(@PathVariable long zaliha_id) {
		Zaliha zaliha = zalihaService.getZalihaById(zaliha_id);
		if(zaliha != null) {
			return new ResponseEntity<Zaliha>(zaliha, HttpStatus.OK);
		}
		return new ResponseEntity<Zaliha>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/createZaliha")
	public ResponseEntity<Zaliha> createZaliha(@RequestBody ZalihaDto zalihaDto) {
		Zaliha zaliha = zalihaService.createZaliha(zalihaDto);
		if(zaliha != null) {
			return new ResponseEntity<Zaliha>(zaliha, HttpStatus.CREATED);
		}
		return new ResponseEntity<Zaliha>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/updateZaliha/{zaliha_id}")
	public ResponseEntity<Zaliha> updateZaliha(@PathVariable long zaliha_id, @RequestBody ZalihaDto zalihaDto) {
		Zaliha zaliha = zalihaService.updateZaliha(zaliha_id, zalihaDto);
		if(zaliha != null) {
			return new ResponseEntity<Zaliha>(zaliha, HttpStatus.OK);
		}
		return new ResponseEntity<Zaliha>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/deleteZaliha/{long_id}")
	public ResponseEntity<Zaliha> deleteZaliha(@PathVariable long zaliha_id) {
		Zaliha zaliha = zalihaService.deleteZaliha(zaliha_id);
		if(zaliha != null) {
			return new ResponseEntity<Zaliha>(zaliha, HttpStatus.OK);
		}
		return new ResponseEntity<Zaliha>(HttpStatus.BAD_REQUEST);
	}
}
