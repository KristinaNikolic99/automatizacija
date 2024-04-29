package com.automatizacija.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetaljiSastavaDto {

	private float kolicina;
	private long sastojak_id;
	private long sok_id;
}
