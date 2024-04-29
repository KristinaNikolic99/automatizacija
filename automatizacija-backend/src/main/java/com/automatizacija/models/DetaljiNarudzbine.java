package com.automatizacija.models;

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
@Table(name = "detalji_narudzbine")
public class DetaljiNarudzbine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long detalji_narudzbine_id;
	
	@Column(name = "kolicina", nullable = false)
	private float kolicina;
	
	@ManyToOne
	@JoinColumn(name = "sok", nullable = false)
	private Sok sok;
	
	@ManyToOne
	@JoinColumn(name = "narudzbina", nullable = false)
	private Narudzbina narudzbina;
}
