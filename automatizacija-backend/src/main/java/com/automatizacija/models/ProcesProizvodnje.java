package com.automatizacija.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "proces_proizvodnje")
public class ProcesProizvodnje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long proces_proizvodnje_id;
	
	@Column(name = "aktivna", nullable = false)
	private boolean aktivna;
	
	@Column(name = "datum_pocetka", nullable = false)
	private LocalDateTime datumPocetka;
	
	@ManyToOne
	@JoinColumn(name = "proizvodnja")
	private Proizvodnja proizvodnja;
	
	@ManyToOne
	@JoinColumn(name = "korisnik")
	private Korisnik korisnik;
}
