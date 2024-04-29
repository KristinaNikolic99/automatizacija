package com.automatizacija.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.automatizacija.models.Proizvodnja;

@Repository
public interface ProizvodnjaRepository extends JpaRepository<Proizvodnja, Long> {

	Proizvodnja findById(long proizvodnja_id);
}
