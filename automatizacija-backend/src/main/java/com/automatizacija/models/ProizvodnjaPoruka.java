package com.automatizacija.models;
import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProizvodnjaPoruka {
	
	String poruka;
	
	HttpStatus status;

}
