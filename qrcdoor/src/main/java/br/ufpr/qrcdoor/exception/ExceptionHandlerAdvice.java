package br.ufpr.qrcdoor.exception;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@SuppressWarnings("rawtypes")
@ControllerAdvice
public class ExceptionHandlerAdvice {

	private static final Logger log = Logger.getLogger(ExceptionHandlerAdvice.class);

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity handleException(ResourceNotFoundException e) {
		log.error(e);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body("O recurso informado na requisição não foi encontrado.");
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity handleBusinessException(BusinessException e) {
		log.error(e);
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
				.body(e);
	}

	@ExceptionHandler({ JsonParseException.class, JsonMappingException.class, IOException.class })
	public ResponseEntity handleParseException(Exception e) {
		log.error(e);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parâmetros inválidos. Verifique os parâmetros e tente novamente.");
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity handleException(Exception e) {
		log.error(e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Sistema temporariamente indisponível. Tente novamente mais tarde.");
	}
}
