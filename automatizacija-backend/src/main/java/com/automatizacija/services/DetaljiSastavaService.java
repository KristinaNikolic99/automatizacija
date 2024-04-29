package com.automatizacija.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automatizacija.dtos.DetaljiSastavaDto;
import com.automatizacija.models.DetaljiSastava;
import com.automatizacija.models.Sastojak;
import com.automatizacija.models.Sok;
import com.automatizacija.repositories.DetaljiSastavaRepository;
import com.automatizacija.repositories.SastojakRepository;
import com.automatizacija.repositories.SokRepository;

@Service
public class DetaljiSastavaService {

	@Autowired
	private DetaljiSastavaRepository detaljiSastavaRepository;
	
	@Autowired
	private SastojakRepository sastojakRepository;
	
	@Autowired
	private SokRepository sokRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<DetaljiSastava> getADetaljiSastavata() {
		return detaljiSastavaRepository.findAll(); 
	}
	
	public DetaljiSastava getDetaljiSastavaById(long detalji_sastava_id) {
		return detaljiSastavaRepository.findById(detalji_sastava_id);
	}
	
	public DetaljiSastava createDetaljiSastava(DetaljiSastavaDto detaljiSastavaDto) {
		DetaljiSastava detaljiSastava = this.modelMapper.map(detaljiSastavaDto, DetaljiSastava.class);
		Sastojak sastojak = sastojakRepository.findById(detaljiSastavaDto.getSastojak_id());
		Sok sok = sokRepository.findById(detaljiSastavaDto.getSok_id());
		detaljiSastava.setSastojak(sastojak);
		detaljiSastava.setSok(sok);
		return detaljiSastavaRepository.save(detaljiSastava);
	}
	
	public DetaljiSastava updaDetaljiSastava(long detalji_sastava_id, DetaljiSastavaDto detaljiSastavaDto) {
		DetaljiSastava detaljiSastava = detaljiSastavaRepository.findById(detalji_sastava_id);
		Sastojak sastojak = sastojakRepository.findById(detaljiSastavaDto.getSastojak_id());
		Sok sok = sokRepository.findById(detaljiSastavaDto.getSok_id());
		detaljiSastava.setKolicina(detaljiSastavaDto.getKolicina());
		detaljiSastava.setSastojak(sastojak);
		detaljiSastava.setSok(sok);
		return detaljiSastavaRepository.save(detaljiSastava);
	}
	
	public DetaljiSastava deleteDetaljiSastava(long detalji_sastava_id) {
		DetaljiSastava detaljiSastava = detaljiSastavaRepository.findById(detalji_sastava_id);
		if(detaljiSastava != null) {
			detaljiSastavaRepository.delete(detaljiSastava);
			return detaljiSastava;
		}
		return null;
	}
	
	
}
