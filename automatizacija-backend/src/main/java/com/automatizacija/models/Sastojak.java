package com.automatizacija.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "sastojak")
public class Sastojak {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long sastojak_id;
	
	@Column(name = "naziv", nullable = false)
	private String naziv;
	
	@Column(name = "vrsta_ploda", nullable = false)
	private String vrstaPloda;
	
	@OneToOne(mappedBy = "sastojak")
	@JsonIgnore
	private Zaliha zaliha;
	
	@OneToMany(mappedBy = "sastojak")
	@JsonIgnore
	private List<DetaljiSastava> detaljiSastava;
}
