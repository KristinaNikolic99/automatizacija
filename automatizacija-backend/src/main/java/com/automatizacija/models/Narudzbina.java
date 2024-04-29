package com.automatizacija.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "narudzbina")
public class Narudzbina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long narudzbina_id;
	
	@Column(name = "broj_narudzbine", nullable = false)
	private String brojNarudzbine;
	
	@Column(name = "datum_narudzbine", nullable = false)
	private LocalDateTime datumNarudzbine;
	
	@Column(name = "ukupna_cena", nullable = false)
	private float ukupnaCena;
	
	@Column(name = "komentar")
	private String komentar;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private Status status;
	
	@ManyToOne
	@JoinColumn(name = "korisnik", nullable = false)
	private Korisnik korisnik;
	
	

}
