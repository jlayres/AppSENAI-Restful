package br.com.senai.exception;

import java.util.ArrayList;
import java.util.List;

// Classe responsável por montar o erro

public class ValidationError extends StandardError {

	//campos que deram erro de validação
	private List<FieldMessage> erros = new ArrayList();
	
	public ValidationError() {
		super();
	}

	public ValidationError(Long timeStamp, Integer status, String error, String message, String path) {
		super(timeStamp, status, error, message, path);
	}
	
	public List<FieldMessage> getErrors() {
		return erros;
	}
	
	public void addError (String fieldName, String message) {
		this.erros.add(new FieldMessage(fieldName, message));
	}

}
