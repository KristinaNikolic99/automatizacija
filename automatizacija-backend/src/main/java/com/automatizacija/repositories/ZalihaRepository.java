package com.automatizacija.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.automatizacija.models.Sastojak;
import com.automatizacija.models.Zaliha;

@Repository
public interface ZalihaRepository extends JpaRepository<Zaliha, Long> {

	Zaliha findById(long zaliha_id);
	
	Zaliha findBySastojak(Sastojak sastojak);
}
