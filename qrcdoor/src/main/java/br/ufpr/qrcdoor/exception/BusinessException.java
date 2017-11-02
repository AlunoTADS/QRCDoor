package br.ufpr.qrcdoor.exception;

import java.util.HashMap;
import java.util.List;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private HashMap<String, List<String>> errors;
	
	public BusinessException() {
	}

	public BusinessException(String message, HashMap<String, List<String>> errors) {
		super(message);
		this.errors = errors;
	}

	public HashMap<String, List<String>> getErrors() {
		return errors;
	}

	public void setErrors(HashMap<String, List<String>> errors) {
		this.errors = errors;
	}

}
