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

import br.ufpr.qrcdoor.entity.Acesso;
import br.ufpr.qrcdoor.service.AcessoService;

@SuppressWarnings("rawtypes")
@RestController
public class AcessoController {

	@Autowired
	private AcessoService acessoService;
	
	@GetMapping("/acessos")
	public ResponseEntity get() throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.acessoService.findAll());
	}

	@GetMapping("/acesso/{id}")
	public ResponseEntity get(@PathVariable Long id) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.acessoService.findOne(id));
	}

	@PostMapping("/acessos")
	public ResponseEntity post(@RequestBody String body) throws Exception {
		Acesso acesso = new ObjectMapper().readValue(body, Acesso.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.acessoService.save(acesso));
	}

	@PutMapping("/acessos")
	public ResponseEntity put(@RequestBody String body) throws Exception {
		Acesso acesso = new ObjectMapper().readValue(body, Acesso.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.acessoService.save(acesso));
	}

	@DeleteMapping("/acessos/{id}")
	public ResponseEntity delete(@PathVariable Long id) throws Exception {
		this.acessoService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}
	
}