package com.automatizacija.email;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface EmailTokenRepository extends JpaRepository<EmailToken, Long> {

	EmailToken findByToken(String token);

    @Transactional
    @Modifying
    @Query("update EmailToken e set e.datumPotvrde=?2 where e.token=?1")
    int updateDatumPotvrde(String token, LocalDateTime datumPotvrde);
}
