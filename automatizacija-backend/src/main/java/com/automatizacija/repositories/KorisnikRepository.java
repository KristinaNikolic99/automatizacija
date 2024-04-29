package com.automatizacija.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.automatizacija.models.Korisnik;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

	Korisnik findById(long korisnik_id);
	Korisnik findByEmail(String email);
}
