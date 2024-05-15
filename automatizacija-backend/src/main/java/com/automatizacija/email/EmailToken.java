package com.automatizacija.email;

import java.time.LocalDateTime;

import com.automatizacija.models.Korisnik;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EmailToken {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="token",nullable = false)
    private String token;

    @Column(name = "datum_kreiranja",nullable = false)
    private LocalDateTime datumKreiranja;

    @Column(name = "datum_isticanja",nullable = false)
    private LocalDateTime datumIsticanja;

    @Column(name = "datum_potvrde")
    private LocalDateTime datumPotvrde;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="korisnik_id",nullable = false)
    private Korisnik korisnik;

    public EmailToken(String token, LocalDateTime datumKreiranja,LocalDateTime datumIsticanja,Korisnik korisnik){

        this.token=token;
        this.datumKreiranja=datumKreiranja;
        this.datumIsticanja=datumIsticanja;
        this.korisnik=korisnik;
    }
}
