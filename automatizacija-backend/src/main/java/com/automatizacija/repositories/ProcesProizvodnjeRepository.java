package com.automatizacija.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.automatizacija.models.Korisnik;
import com.automatizacija.models.ProcesProizvodnje;

@Repository
public interface ProcesProizvodnjeRepository extends JpaRepository<ProcesProizvodnje, Long> {
	
	ProcesProizvodnje findById(long proces_proizvodnje_id);
	
	List<ProcesProizvodnje> findByAktivnaTrue();
	
	List<ProcesProizvodnje> findByKorisnik(Korisnik korisnik);


}
