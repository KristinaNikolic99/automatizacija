package com.automatizacija.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.automatizacija.dtos.KorisnikDto;
import com.automatizacija.email.EmailService;
import com.automatizacija.email.EmailToken;
import com.automatizacija.email.EmailTokenService;
import com.automatizacija.models.Korisnik;
import com.automatizacija.models.TipKorisnika;
import com.automatizacija.repositories.KorisnikRepository;

@Service
public class KorisnikService implements UserDetailsService {

	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailTokenService emailTokenService;
	
	@Autowired
	private EmailService emailService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Korisnik korisnik = korisnikRepository.findByEmail(email);
        if(korisnik==null){
            throw new UsernameNotFoundException("Korisnik nije pronadjen u bazi" + email);
        } else if(korisnik.isStatusNaloga() == false){
            throw new UsernameNotFoundException("Korisnik nije aktivirao svoj nalog preko email-a");
        }
        Collection<SimpleGrantedAuthority> authority = new ArrayList<>();
        authority.add(new SimpleGrantedAuthority(korisnik.getTipKorisnika().toString()));
        return new User(korisnik.getEmail(),korisnik.getPassword(), authority);
	}
	
	public List<Korisnik> getAllKorisniks() {
		return korisnikRepository.findAll();
	}
	
	public Korisnik getKorisnikById(long korisnik_id) {
		return korisnikRepository.findById(korisnik_id);
	}
	
	public Korisnik register(KorisnikDto korisnikDto) {
		Korisnik korisnik = this.modelMapper.map(korisnikDto, Korisnik.class);
		korisnik.setPassword(passwordEncoder.encode(korisnik.getPassword()));
		korisnik.setTipKorisnika(TipKorisnika.Kupac);
		if (korisnikRepository.findByEmail(korisnik.getEmail()) != null) {
			return null;
		}
		String token = UUID.randomUUID().toString();
		EmailToken emailToken = new EmailToken(token, LocalDateTime.now(),LocalDateTime.now().plusMinutes(15), korisnik);
		emailTokenService.saveEmailToken(emailToken);
		String link = "http://localhost:8080/api/confirm?token=" + token;
		emailService.send(korisnik.getEmail(),
                emailService.emailKreiranje(korisnik.getIme(),link));
		return korisnikRepository.save(korisnik);
	}
	
	public Korisnik logIn(String email, String password) {
		Korisnik korisnik = korisnikRepository.findByEmail(email);
		if(!korisnik.isStatusNaloga()) {
			return null;
		}
		if(korisnik != null && passwordEncoder.matches(password, korisnik.getPassword())) {
			return korisnik;
		}
		return null;
	}
	
	public Korisnik updateKorisnik(long korisnik_id, KorisnikDto korisnikDto) {
		Korisnik korisnik = korisnikRepository.findById(korisnik_id);
		korisnik.setIme(korisnikDto.getIme());
		korisnik.setPrezime(korisnikDto.getPrezime());
		korisnik.setEmail(korisnikDto.getEmail());
		korisnik.setPassword(korisnikDto.getPassword());
		korisnik.setTipKorisnika(TipKorisnika.valueOf(korisnikDto.getTipKorisnika()));
		return korisnikRepository.save(korisnik);
	}
	
	public Korisnik deleteKorisnik(long korisnik_id) {
		Korisnik korisnik = korisnikRepository.findById(korisnik_id);
		if(korisnik != null) {
			korisnikRepository.delete(korisnik);
			return korisnik;
		}
		return null;
	}
	
	public String confirmEmailToken(String token) {
		EmailToken emailToken = emailTokenService.getEmailToken(token);
		
		 if(emailToken == null){
	         throw new IllegalStateException("Token nije validan");

	     }
	     if (emailToken.getDatumPotvrde() != null){

	         throw new IllegalStateException("Email je vec verifikovan");
	     }
         if(emailToken.getDatumIsticanja().isBefore(LocalDateTime.now())){
             throw new IllegalStateException("Token je istekao");
         }
         Korisnik korisnik = emailToken.getKorisnik();
         korisnik.setStatusNaloga(true);
         emailTokenService.updateDatumPotvrde(emailToken);
         korisnikRepository.save(korisnik);
         return "Korisnik je uspesno verifikovao svoj email";
	}
}
