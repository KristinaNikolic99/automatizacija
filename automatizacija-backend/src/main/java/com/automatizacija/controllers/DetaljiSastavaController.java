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

import com.automatizacija.dtos.DetaljiSastavaDto;
import com.automatizacija.models.DetaljiSastava;
import com.automatizacija.services.DetaljiSastavaService;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class DetaljiSastavaController {

	@Autowired
	private DetaljiSastavaService detaljiSastavaService;
	
	@GetMapping("/detaljiSastava")
	public ResponseEntity<List<DetaljiSastava>> getAllDetaljiSastava() {
		return new ResponseEntity<List<DetaljiSastava>>(detaljiSastavaService.getADetaljiSastavata(), HttpStatus.OK);
	}
	
	@GetMapping("/detaljiSastava/{detalji_sastava_id}")
	public ResponseEntity<DetaljiSastava> getDetaljiSastavaById(@PathVariable long detalji_sastava_id) {
		DetaljiSastava detaljiSastava = detaljiSastavaService.getDetaljiSastavaById(detalji_sastava_id);
		if(detaljiSastava != null) {
			return new ResponseEntity<DetaljiSastava>(detaljiSastava, HttpStatus.OK);
		}
		return new ResponseEntity<DetaljiSastava>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/createDetaljiSastava")
	public ResponseEntity<DetaljiSastava> createDetaljiSastav(@RequestBody DetaljiSastavaDto detaljiSastavaDto) {
		DetaljiSastava detaljiSastava = detaljiSastavaService.createDetaljiSastava(detaljiSastavaDto);
		if(detaljiSastava != null) {
			return new ResponseEntity<DetaljiSastava>(detaljiSastava, HttpStatus.CREATED);
		}
		return new ResponseEntity<DetaljiSastava>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/updateDetaljiSastava/{detalji_sastava_id}")
	public ResponseEntity<DetaljiSastava> updateDetaljiSastava(@PathVariable long detalji_sastava_id, @RequestBody DetaljiSastavaDto detaljiSastavaDto) {
		DetaljiSastava detaljiSastava = detaljiSastavaService.updaDetaljiSastava(detalji_sastava_id, detaljiSastavaDto);
		if(detaljiSastava != null) {
			return new ResponseEntity<DetaljiSastava>(detaljiSastava, HttpStatus.OK);
		}
		return new ResponseEntity<DetaljiSastava>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/deleteDetaljiSastava/{detalji_sastava_id}")
	public ResponseEntity<DetaljiSastava> deleteDetaljiSastava(@PathVariable long detalji_sastava_id) {
		DetaljiSastava detaljiSastava = detaljiSastavaService.deleteDetaljiSastava(detalji_sastava_id);
		if(detaljiSastava != null) {
			return new ResponseEntity<DetaljiSastava>(detaljiSastava, HttpStatus.OK);
		}
		return new ResponseEntity<DetaljiSastava>(HttpStatus.BAD_REQUEST);
	}
}
