package com.automatizacija.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "zaliha")
public class Zaliha {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long zaliha_id;
	
	@Column(name = "kolicina", nullable = false)
	private int kolicina;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "mera", nullable = false)
	private Mera mera;
	
	@OneToOne
	@JoinColumn(name = "sastojak", nullable = false)
	private Sastojak sastojak;
}
