package com.automatizacija.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KorisnikDto {

	private String ime;
	private String prezime;
	private String email;
	private String password;
	private String tipKorisnika;
}
