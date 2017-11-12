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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufpr.qrcdoor.entity.Estrutura;
import br.ufpr.qrcdoor.service.EstruturaService;

@SuppressWarnings("rawtypes")
@RestController
public class EstruturaController {
	
	@Autowired
	private EstruturaService estruturaService;
	
	@GetMapping("/estruturas")
	@ResponseBody
	public Page<Estrutura> get(@RequestParam(required=false, name="q") String query, Pageable pageable) throws Exception {
		return this.estruturaService.find(query, pageable);
	}

	@GetMapping("/estrutura/{id}")
	public ResponseEntity get(@PathVariable Long id) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.estruturaService.findOne(id));
	}

	@PostMapping("/estruturas")
	public ResponseEntity post(@RequestBody String body) throws Exception {
		Estrutura estrutura = new ObjectMapper().readValue(body, Estrutura.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.estruturaService.save(estrutura));
	}

	@PutMapping("/estruturas")
	public ResponseEntity put(@RequestBody String body) throws Exception {
		Estrutura estrutura = new ObjectMapper().readValue(body, Estrutura.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.estruturaService.save(estrutura));
	}

	@DeleteMapping("/estruturas/{id}")
	public ResponseEntity delete(@PathVariable Long id) throws Exception {
		this.estruturaService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}

}
