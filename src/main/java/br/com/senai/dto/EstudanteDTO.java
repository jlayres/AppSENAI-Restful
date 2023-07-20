package br.com.senai.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class EstudanteDTO {

	@Getter
	@Setter
	private Integer id;
	
	@Getter
	@Setter
	@NotNull
	private String nome;
	
	@Getter
	@Setter
	private String sobreNome;
	
	@NotNull
	private String email;
	
	@Getter
	@Setter
	private LocalDate dataNascimento;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
