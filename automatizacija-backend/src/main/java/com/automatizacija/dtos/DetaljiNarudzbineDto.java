package com.automatizacija.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetaljiNarudzbineDto {

	private float kolicina;
	private long sok_id;
	private long narudzbina_id;
}
