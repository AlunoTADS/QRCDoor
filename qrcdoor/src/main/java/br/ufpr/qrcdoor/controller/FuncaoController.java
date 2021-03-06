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

import br.ufpr.qrcdoor.entity.Funcao;
import br.ufpr.qrcdoor.service.FuncaoService;

@SuppressWarnings("rawtypes")
@RestController
public class FuncaoController {
	
	@Autowired
	private FuncaoService funcaoService;
	
	@GetMapping("/funcao")
	public Page<Funcao> get(@RequestParam(name="q") String query, Pageable pageable) throws Exception {
		Funcao funcao = new ObjectMapper().readValue(query, Funcao.class);
		return this.funcaoService.find(funcao, pageable);
	}

	@GetMapping("/funcao/{id}")
	public ResponseEntity get(@PathVariable Long id) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.funcaoService.findOne(id));
	}

	@PostMapping("/funcao")
	public ResponseEntity post(@RequestBody String body) throws Exception {
		Funcao funcao = new ObjectMapper().readValue(body, Funcao.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.funcaoService.save(funcao));
	}

	@PutMapping("/funcao")	public ResponseEntity put(@RequestBody String body) throws Exception {
		Funcao funcao = new ObjectMapper().readValue(body, Funcao.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.funcaoService.save(funcao));
	}

	@DeleteMapping("/funcao/{id}")
	public ResponseEntity delete(@PathVariable Long id) throws Exception {
		this.funcaoService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}

}
