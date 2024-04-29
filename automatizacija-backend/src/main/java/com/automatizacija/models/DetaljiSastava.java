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
@Table(name = "detalji_sastava")
public class DetaljiSastava {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long detalji_sastava_id;
	
	@Column(name = "kolicina", nullable = false)
	private float kolicina;
	
	@ManyToOne
	@JoinColumn(name = "sastojak", nullable = false)
	private Sastojak sastojak;
	
	@ManyToOne
	@JoinColumn(name = "sok", nullable = false)
	private Sok sok;

}
