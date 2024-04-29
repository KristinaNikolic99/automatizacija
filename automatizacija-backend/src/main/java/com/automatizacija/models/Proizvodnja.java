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
@Table(name = "proizvodnja")
public class Proizvodnja {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long proizvodnja_id;
	
	@Column(name = "masina", nullable = false)
	private String masina;
	
	@ManyToOne
	@JoinColumn(name = "sok")
	private Sok sok;

}
