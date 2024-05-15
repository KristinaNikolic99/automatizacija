package com.automatizacija.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.automatizacija.models.Masina;
import com.automatizacija.models.Proizvodnja;
import com.automatizacija.models.Sok;

@Repository
public interface ProizvodnjaRepository extends JpaRepository<Proizvodnja, Long> {

	Proizvodnja findById(long proizvodnja_id);
	
	@Query(value = "select * from proizvodnja where sok = :sok_id", nativeQuery=true) 
	List<Proizvodnja> findBySokId(long sok_id);
	
	Proizvodnja findBySokAndMasina(Sok sok, Masina masina);
}
