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

import br.ufpr.qrcdoor.entity.FuncaoPessoa;
import br.ufpr.qrcdoor.service.FuncaoPessoaService;

@SuppressWarnings("rawtypes")
@RestController
public class FuncaoPessoaController {
	
	@Autowired
	private FuncaoPessoaService funcaoPessoaService;
	
	@GetMapping("/funcao-pessoa")
	public ResponseEntity get() throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.funcaoPessoaService.findAll());
	}

	@GetMapping("/funcao-pessoa/{id}")
	public ResponseEntity get(@PathVariable Long id) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.funcaoPessoaService.findOne(id));
	}

	@PostMapping("/funcao-pessoa")
	public ResponseEntity post(@RequestBody String body) throws Exception {
		FuncaoPessoa funcaoPessoa = new ObjectMapper().readValue(body, FuncaoPessoa.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.funcaoPessoaService.save(funcaoPessoa));
	}

	@PutMapping("/funcao-pessoa")
	public ResponseEntity put(@RequestBody String body) throws Exception {
		FuncaoPessoa funcaoPessoa = new ObjectMapper().readValue(body, FuncaoPessoa.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.funcaoPessoaService.save(funcaoPessoa));
	}

	@DeleteMapping("/funcao-pessoa/{id}")
	public ResponseEntity delete(@PathVariable Long id) throws Exception {
		this.funcaoPessoaService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}

}
