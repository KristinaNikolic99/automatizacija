package com.automatizacija.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.automatizacija.models.DetaljiSastava;

@Repository
public interface DetaljiSastavaRepository extends JpaRepository<DetaljiSastava, Long> {

	DetaljiSastava findById(long detalji_sastava_id);
}
