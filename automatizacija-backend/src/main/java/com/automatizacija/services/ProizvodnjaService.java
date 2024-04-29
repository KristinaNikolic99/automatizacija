package com.automatizacija.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automatizacija.dtos.ProizvodnjaDto;
import com.automatizacija.models.Proizvodnja;
import com.automatizacija.models.Sok;
import com.automatizacija.repositories.ProizvodnjaRepository;
import com.automatizacija.repositories.SokRepository;

@Service
public class ProizvodnjaService {

	@Autowired
	private ProizvodnjaRepository proizvodnjaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private SokRepository sokRepository;
	
	public List<Proizvodnja> getAllProizvodnjas() {
		return proizvodnjaRepository.findAll();
	}
	
	public Proizvodnja getProizvodnjaById(long proizvodnja_id) {
		return proizvodnjaRepository.findById(proizvodnja_id);
	}
	
	public Proizvodnja createProizvodnja(ProizvodnjaDto proizvodnjaDto) {
		Proizvodnja proizvodnja = this.modelMapper.map(proizvodnjaDto, Proizvodnja.class);
		Sok sok = sokRepository.findById(proizvodnjaDto.getSok_id());
		proizvodnja.setSok(sok);
		return proizvodnjaRepository.save(proizvodnja);
	}
	
	public Proizvodnja updateProizvodnja(long proizvodnja_id, ProizvodnjaDto proizvodnjaDto) {
		Proizvodnja proizvodnja = proizvodnjaRepository.findById(proizvodnja_id);
		Sok sok = sokRepository.findById(proizvodnjaDto.getSok_id());
		proizvodnja.setMasina(proizvodnjaDto.getMasina());
		proizvodnja.setSok(sok);
		return proizvodnjaRepository.save(proizvodnja);
	}
	
	public Proizvodnja deleteProizvodnja(long proizvodnja_id) {
		Proizvodnja proizvodnja = proizvodnjaRepository.findById(proizvodnja_id);
		if(proizvodnja != null) {
			proizvodnjaRepository.delete(proizvodnja);
			return proizvodnja;
		}
		return null;
	}
}
