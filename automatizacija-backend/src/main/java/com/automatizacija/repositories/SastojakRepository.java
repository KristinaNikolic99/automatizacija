package com.automatizacija.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.automatizacija.models.Sastojak;

@Repository
public interface SastojakRepository extends JpaRepository<Sastojak, Long> {

	Sastojak findById(long sastojak_id);
}
