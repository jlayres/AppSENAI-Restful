package br.com.senai.exception;

//Classe que representa o formato json na mensagem de erro

public class ObjectNotFoundException extends RuntimeException {
	
	public ObjectNotFoundException (String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectNotFoundException (String message) {
		super(message);
	}
}
