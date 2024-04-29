package com.automatizacija.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SokDto {

	private String vrsta;
	private String opis;
	private float kolicina;
	private float cena;
}
