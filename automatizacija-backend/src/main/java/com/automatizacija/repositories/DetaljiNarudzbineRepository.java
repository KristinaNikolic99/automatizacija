package com.automatizacija.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.automatizacija.models.DetaljiNarudzbine;

@Repository
public interface DetaljiNarudzbineRepository extends JpaRepository<DetaljiNarudzbine, Long> {

	DetaljiNarudzbine findById(long detalji_narudzbine_id);
}
