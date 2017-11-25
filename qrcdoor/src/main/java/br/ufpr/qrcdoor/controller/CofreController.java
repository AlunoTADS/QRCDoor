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

import br.ufpr.qrcdoor.entity.Cofre;
import br.ufpr.qrcdoor.service.CofreService;

@SuppressWarnings("rawtypes")
@RestController
public class CofreController {
	
	@Autowired
	private CofreService cofreService;
	
	@GetMapping("/cofre")
	@ResponseBody
	public Page<Cofre> get(@RequestParam(name="q") String query, Pageable pageable) throws Exception {
		Cofre cofre = new ObjectMapper().readValue(query, Cofre.class);
		return this.cofreService.find(cofre, pageable);
	}

	@GetMapping("/cofre/{id}")
	public ResponseEntity get(@PathVariable Long id) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.cofreService.findOne(id));
	}

	@PostMapping("/cofre")
	public ResponseEntity post(@RequestBody String body) throws Exception {
		Cofre cofre = new ObjectMapper().readValue(body, Cofre.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.cofreService.save(cofre));
	}

	@PutMapping("/cofre")
	public ResponseEntity put(@RequestBody String body) throws Exception {
		Cofre cofre = new ObjectMapper().readValue(body, Cofre.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.cofreService.save(cofre));
	}

	@DeleteMapping("/cofre/{id}")
	public ResponseEntity delete(@PathVariable Long id) throws Exception {
		this.cofreService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}

}
