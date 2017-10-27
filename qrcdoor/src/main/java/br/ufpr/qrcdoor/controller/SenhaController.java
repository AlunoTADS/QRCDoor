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

import br.ufpr.qrcdoor.entity.Senha;
import br.ufpr.qrcdoor.service.SenhaService;

@SuppressWarnings("rawtypes")
@RestController
public class SenhaController {
	
	@Autowired
	private SenhaService senhaService;
	
	@GetMapping("/senhas")
	public ResponseEntity get() throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.senhaService.findAll());
	}

	@GetMapping("/senha/{id}")
	public ResponseEntity get(@PathVariable Long id) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.senhaService.findOne(id));
	}

	@PostMapping("/senhas")
	public ResponseEntity post(@RequestBody String body) throws Exception {
		Senha senha = new ObjectMapper().readValue(body, Senha.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.senhaService.save(senha));
	}

	@PutMapping("/senhas")
	public ResponseEntity put(@RequestBody String body) throws Exception {
		Senha senha = new ObjectMapper().readValue(body, Senha.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.senhaService.save(senha));
	}

	@DeleteMapping("/senhas/{id}")
	public ResponseEntity delete(@PathVariable Long id) throws Exception {
		this.senhaService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}

}
