package com.automatizacija.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.automatizacija.models.Sok;

@Repository
public interface SokRepository extends JpaRepository<Sok, Long> {

	Sok findById(long sok_id);
}
