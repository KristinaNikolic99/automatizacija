package com.automatizacija.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.automatizacija.models.Masina;

@Repository
public interface MasinaRepository extends JpaRepository<Masina, Long> {
	
	Masina findById(long masina_id);
}
