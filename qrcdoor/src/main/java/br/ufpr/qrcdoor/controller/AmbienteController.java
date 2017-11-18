package br.ufpr.qrcdoor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufpr.qrcdoor.entity.Ambiente;
import br.ufpr.qrcdoor.service.AmbienteService;

@SuppressWarnings("rawtypes")
@RestController
public class AmbienteController {

	@Autowired
	private AmbienteService ambienteService;
	
	@GetMapping("/ambiente")
	public ResponseEntity get() throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.ambienteService.findAll());
	}

	@GetMapping("/ambiente/{id}")
	public ResponseEntity get(@PathVariable Long id) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.ambienteService.findOne(id));
	}

	@PostMapping("/ambiente")
	public ResponseEntity post(@RequestBody String body) throws Exception {
		Ambiente ambiente = new ObjectMapper().readValue(body, Ambiente.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.ambienteService.save(ambiente));
	}

	@PutMapping("/ambiente")
	public ResponseEntity put(@RequestBody String body) throws Exception {
		Ambiente ambiente = new ObjectMapper().readValue(body, Ambiente.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.ambienteService.save(ambiente));
	}

	@DeleteMapping("/ambiente/{id}")
	public ResponseEntity delete(@PathVariable Long id) throws Exception {
		this.ambienteService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}
}
