package com.automatizacija.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "masina")
public class Masina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long masina_id;
	
	@Column(name = "naziv", nullable = false)
	private String naziv;
	
	@Column(name = "serijski_broj", nullable = false)
	private String serijskiBroj;

}
