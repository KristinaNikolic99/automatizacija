package com.automatizacija.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automatizacija.dtos.SastojakDto;
import com.automatizacija.models.Sastojak;
import com.automatizacija.repositories.SastojakRepository;

@Service
public class SastojakService {

	@Autowired
	private SastojakRepository sastojakRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<Sastojak> getAllSastojaks() {
		return sastojakRepository.findAll();
	}
	
	public Sastojak getSastojakById(long sastojak_id) {
		return sastojakRepository.findById(sastojak_id);
	}
	
	public Sastojak createSastojak(SastojakDto sastojakDto) {
		Sastojak sastojak = this.modelMapper.map(sastojakDto, Sastojak.class);
		return sastojakRepository.save(sastojak);
	}
	
	public Sastojak updateSastojak(long sastojak_id, SastojakDto sastojakDto) {
		Sastojak sastojak = sastojakRepository.findById(sastojak_id);
		sastojak.setNaziv(sastojakDto.getNaziv());
		sastojak.setVrstaPloda(sastojakDto.getVrstaPloda());
		return sastojakRepository.save(sastojak);
	}
	
	public Sastojak deleteSastojak(long sastojak_id) {
		Sastojak sastojak = sastojakRepository.findById(sastojak_id);
		if(sastojak != null) {
			sastojakRepository.delete(sastojak);
			return sastojak;
		}
		return null;
	}
}
