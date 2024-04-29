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
import com.automatizacija.dtos.SokDto;
import com.automatizacija.models.Sok;
import com.automatizacija.services.SokService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class SokController {

	@Autowired
	private SokService sokService;
	
	@GetMapping("/sokovi")
	public ResponseEntity<List<Sok>> getAllSoks() {
		return new ResponseEntity<List<Sok>>(sokService.getAllSoks(), HttpStatus.OK);
	}
	
	@GetMapping("/sok/{sok_id}")
	public ResponseEntity<Sok> getSokById(@PathVariable long sok_id) {
		Sok sok = sokService.getSokById(sok_id);
		if(sok != null) {
			return new ResponseEntity<Sok>(sok, HttpStatus.OK);
		}
		return new ResponseEntity<Sok>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/createSok")
	public ResponseEntity<Sok> createSok(@RequestBody SokDto sokDto) {
		Sok sok = sokService.createSok(sokDto);
		if(sok != null) {
			return new ResponseEntity<Sok>(sok, HttpStatus.CREATED);
		}
		return new ResponseEntity<Sok>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("updateSok/{sok_id}")
	public ResponseEntity<Sok> updateSok(@PathVariable long sok_id, @RequestBody SokDto sokDto) {
		Sok sok = sokService.updateSok(sok_id, sokDto);
		if (sok != null) {
			return new ResponseEntity<Sok>(sok, HttpStatus.OK);
		}
		return new ResponseEntity<Sok>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("deleteSok/{sok_id}")
	public ResponseEntity<Sok> deleteSok(@PathVariable long sok_id) {
		Sok sok = sokService.deleteSok(sok_id);
		if(sok != null) {
			return new ResponseEntity<Sok>(sok, HttpStatus.OK);
		}
		return new ResponseEntity<Sok>(HttpStatus.BAD_REQUEST);
	}
}
