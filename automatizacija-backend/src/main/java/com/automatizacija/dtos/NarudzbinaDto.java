package com.automatizacija.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NarudzbinaDto {

	private String brojNarudzbine;
	private LocalDateTime datumNarudzbine;
	private float ukupnaCena;
	private String komentar;
	private String status;
	private long korisnik_id;
}
