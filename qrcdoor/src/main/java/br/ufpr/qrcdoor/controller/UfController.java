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

import br.ufpr.qrcdoor.entity.Uf;
import br.ufpr.qrcdoor.service.UfService;

@SuppressWarnings("rawtypes")
@RestController
public class UfController {
	
	@Autowired
	private UfService ufService;
	
	@GetMapping("/ufs")
	public ResponseEntity get() throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.ufService.findAll());
	}

	@GetMapping("/uf/{id}")
	public ResponseEntity get(@PathVariable Long id) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.ufService.findOne(id));
	}

	@PostMapping("/ufs")
	public ResponseEntity post(@RequestBody String body) throws Exception {
		Uf uf = new ObjectMapper().readValue(body, Uf.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.ufService.save(uf));
	}

	@PutMapping("/ufs")
	public ResponseEntity put(@RequestBody String body) throws Exception {
		Uf uf = new ObjectMapper().readValue(body, Uf.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.ufService.save(uf));
	}

	@DeleteMapping("/ufs/{id}")
	public ResponseEntity delete(@PathVariable Long id) throws Exception {
		this.ufService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}

}
