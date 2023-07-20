package br.com.senai.dto;

import lombok.Getter;
import lombok.Setter;

public class ProfessorDTO {

	
	@Getter
	@Setter
	private Integer matricula;
	
	@Getter
	@Setter
	private String nome;
	
	@Getter
	@Setter
	private String sobreNome;
	
	@Getter
	@Setter
	private String especializacao;
}
