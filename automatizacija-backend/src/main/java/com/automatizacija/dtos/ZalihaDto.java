package com.automatizacija.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ZalihaDto {

	private int kolicina;
	private String mera;
	private long sastojak_id;
}
