package com.automatizacija.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.automatizacija.models.Narudzbina;

@Repository
public interface NarudzbinaRepository extends JpaRepository<Narudzbina, Long> {

	Narudzbina findById(long narudzbina_id);
}
