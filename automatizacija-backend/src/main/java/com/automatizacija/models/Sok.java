package com.automatizacija.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "sok")
public class Sok {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long sok_id;
	
	@Column(name = "vrsta", nullable = false)
	private String vrsta;
	
	@Column(name = "opis", nullable = false)
	private String opis;
	
	@Column(name = "kolicina", nullable = false)
	private float kolicina;
	
	@Column(name = "cena", nullable = false)
	private float cena;
	
	@Column(name = "slika", nullable = false)
	private String slika;
	
	@OneToMany(mappedBy = "sok")
	@JsonIgnore
	private List<DetaljiSastava> detaljiSastava;
	

}
