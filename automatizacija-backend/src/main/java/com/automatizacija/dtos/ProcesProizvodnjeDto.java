package com.automatizacija.dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProcesProizvodnjeDto {
	
	private int kolicina;
	
	private long sok_id;
	
	private long masina_id;

}
