package com.automatizacija.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automatizacija.dtos.SokDto;
import com.automatizacija.models.Sok;
import com.automatizacija.repositories.SokRepository;

@Service
public class SokService {

	@Autowired
	private SokRepository sokRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<Sok> getAllSoks() {
		return sokRepository.findAll();
	}
	
	public Sok getSokById(long sok_id) {
		return sokRepository.findById(sok_id);
	}
	
	public Sok createSok(SokDto sokDto) {
		Sok sok = this.modelMapper.map(sokDto, Sok.class);
		return sokRepository.save(sok);
	}
	
	public Sok updateSok(long sok_id, SokDto sokDto) {
		Sok sok = sokRepository.findById(sok_id);
		sok.setCena(sokDto.getCena());
		sok.setKolicina(sokDto.getKolicina());
		sok.setVrsta(sokDto.getVrsta());
		sok.setOpis(sokDto.getOpis());
		return sokRepository.save(sok);
	}
	
	public Sok deleteSok(long sok_id) {
		Sok sok = sokRepository.findById(sok_id);
		if(sok != null) {
			sokRepository.delete(sok);
			return sok;
		}
		return null;
	}
}
