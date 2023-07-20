package br.com.senai.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity //indica que esta classe refletirá uma tabela
@Data
public class Estudante {

	@Id //indica que este atributo será chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) //define que a chave será auto incrementada
	private Integer id;
	
	private String nome;
	
	private String sobreNome;
	
	private String email;
	
	private LocalDate dataNascimento;
		
}
