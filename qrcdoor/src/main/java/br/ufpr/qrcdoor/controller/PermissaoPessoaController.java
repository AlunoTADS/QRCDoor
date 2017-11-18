package br.ufpr.qrcdoor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufpr.qrcdoor.entity.PermissaoPessoa;
import br.ufpr.qrcdoor.service.PermissaoPessoaService;

@SuppressWarnings("rawtypes")
@RestController
public class PermissaoPessoaController {
	
	@Autowired
	private PermissaoPessoaService permissaoPessoaService;
	
	@GetMapping("/permissao-pessoa")
	public Page<PermissaoPessoa> get(@RequestParam(name="q") String query, Pageable pageable) throws Exception {
		PermissaoPessoa permissaoPessoa = new ObjectMapper().readValue(query, PermissaoPessoa.class);
		return this.permissaoPessoaService.find(permissaoPessoa, pageable);
	}
	
	@GetMapping("/permissao-pessoa/{id}")
	public ResponseEntity get(@PathVariable Long id) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.permissaoPessoaService.findOne(id));
	}

	@PostMapping("/permissao-pessoa")
	public ResponseEntity post(@RequestBody String body) throws Exception {
		PermissaoPessoa permissaoPessoa = new ObjectMapper().readValue(body, PermissaoPessoa.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.permissaoPessoaService.save(permissaoPessoa));
	}

	@PutMapping("/permissao-pessoa")
	public ResponseEntity put(@RequestBody String body) throws Exception {
		PermissaoPessoa permissaoPessoa = new ObjectMapper().readValue(body, PermissaoPessoa.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.permissaoPessoaService.save(permissaoPessoa));
	}

	@DeleteMapping("/permissao-pessoa/{id}")
	public ResponseEntity delete(@PathVariable Long id) throws Exception {
		this.permissaoPessoaService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}

}
