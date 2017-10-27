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

import br.ufpr.qrcdoor.entity.Chave;
import br.ufpr.qrcdoor.service.ChaveService;

@SuppressWarnings("rawtypes")
@RestController
public class ChaveController {
	
	@Autowired
	private ChaveService chaveService;
	
	@GetMapping("/chaves")
	public ResponseEntity get() throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.chaveService.findAll());
	}

	@GetMapping("/chave/{id}")
	public ResponseEntity get(@PathVariable Long id) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.chaveService.findOne(id));
	}

	@PostMapping("/chaves")
	public ResponseEntity post(@RequestBody String body) throws Exception {
		Chave chave = new ObjectMapper().readValue(body, Chave.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.chaveService.save(chave));
	}

	@PutMapping("/chaves")
	public ResponseEntity put(@RequestBody String body) throws Exception {
		Chave chave = new ObjectMapper().readValue(body, Chave.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.chaveService.save(chave));
	}

	@DeleteMapping("/chaves/{id}")
	public ResponseEntity delete(@PathVariable Long id) throws Exception {
		this.chaveService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}

}
