package com.automatizacija.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automatizacija.dtos.ZalihaDto;
import com.automatizacija.models.Mera;
import com.automatizacija.models.Sastojak;
import com.automatizacija.models.Zaliha;
import com.automatizacija.repositories.SastojakRepository;
import com.automatizacija.repositories.ZalihaRepository;

@Service
public class ZalihaService {

	@Autowired
	private ZalihaRepository zalihaRepository;
	
	@Autowired
	private SastojakRepository sastojakRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<Zaliha> getAllZalihas() {
		return zalihaRepository.findAll();
	}
	
	public Zaliha getZalihaById(long zaliha_id) {
		return zalihaRepository.findById(zaliha_id);
	}
	
	public Zaliha createZaliha(ZalihaDto zalihaDto) {
		Zaliha zaliha = this.modelMapper.map(zalihaDto, Zaliha.class);
		Sastojak sastojak = sastojakRepository.findById(zalihaDto.getSastojak_id());
		zaliha.setSastojak(sastojak);
		return zalihaRepository.save(zaliha);
	}
	
	public Zaliha updateZaliha(long zaliha_id, ZalihaDto zalihaDto) {
		Zaliha zaliha = zalihaRepository.findById(zaliha_id);
		Sastojak sastojak = sastojakRepository.findById(zalihaDto.getSastojak_id());
		zaliha.setKolicina(zalihaDto.getKolicina());
		zaliha.setMera(Mera.valueOf(zalihaDto.getMera()));
		zaliha.setSastojak(sastojak);
		return zalihaRepository.save(zaliha);
	}
	
	public Zaliha deleteZaliha(long zaliha_id) {
		Zaliha zaliha = zalihaRepository.findById(zaliha_id);
		if(zaliha != null) {
			zalihaRepository.delete(zaliha);
			return zaliha;
		}
		return null;
	}
}
