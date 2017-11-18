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

import br.ufpr.qrcdoor.entity.Permissao;
import br.ufpr.qrcdoor.service.PermissaoService;

@SuppressWarnings("rawtypes")
@RestController
public class PermissaoController {
	
	@Autowired
	private PermissaoService permissaoService;
	
	@GetMapping("/permissao")
	public ResponseEntity get() throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.permissaoService.findAll());
	}

	@GetMapping("/permissao/{id}")
	public ResponseEntity get(@PathVariable Long id) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.permissaoService.findOne(id));
	}

	@PostMapping("/permissao")
	public ResponseEntity post(@RequestBody String body) throws Exception {
		Permissao permissao = new ObjectMapper().readValue(body, Permissao.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.permissaoService.save(permissao));
	}

	@PutMapping("/permissao")
	public ResponseEntity put(@RequestBody String body) throws Exception {
		Permissao permissao = new ObjectMapper().readValue(body, Permissao.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.permissaoService.save(permissao));
	}

	@DeleteMapping("/permissao/{id}")
	public ResponseEntity delete(@PathVariable Long id) throws Exception {
		this.permissaoService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}

}
