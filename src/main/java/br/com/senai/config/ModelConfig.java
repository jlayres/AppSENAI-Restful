package br.com.senai.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConfig {
	
	@Bean //indica que deve estar disponível todas as vezes em que for usar o mapper
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
