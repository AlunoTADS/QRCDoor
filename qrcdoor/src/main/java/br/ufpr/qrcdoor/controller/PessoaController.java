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

import br.ufpr.qrcdoor.entity.Pessoa;
import br.ufpr.qrcdoor.service.PessoaService;

@SuppressWarnings("rawtypes")
@RestController
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping("/pessoas")
	public Page<Pessoa> get(@RequestParam(required=false, name="q") String query, Pageable pageable) throws Exception {
		return this.pessoaService.find(query, pageable);
	}

	@GetMapping("/pessoa/{id}")
	public ResponseEntity get(@PathVariable Long id) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.pessoaService.findOne(id));
	}

	@PostMapping("/pessoas")
	public ResponseEntity post(@RequestBody String body) throws Exception {
		Pessoa pessoa = new ObjectMapper().readValue(body, Pessoa.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.pessoaService.save(pessoa));
	}

	@PutMapping("/pessoas")
	public ResponseEntity put(@RequestBody String body) throws Exception {
		Pessoa pessoa = new ObjectMapper().readValue(body, Pessoa.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.pessoaService.save(pessoa));
	}

	@DeleteMapping("/pessoas/{id}")
	public ResponseEntity delete(@PathVariable Long id) throws Exception {
		this.pessoaService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}

}
