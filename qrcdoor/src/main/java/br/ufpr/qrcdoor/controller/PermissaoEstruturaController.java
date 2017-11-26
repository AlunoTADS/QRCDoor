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

import br.ufpr.qrcdoor.entity.PermissaoEstrutura;
import br.ufpr.qrcdoor.entity.PermissaoEstrutura.PermissaoEstruturaPK;
import br.ufpr.qrcdoor.service.PermissaoEstruturaService;

@SuppressWarnings("rawtypes")
@RestController
public class PermissaoEstruturaController {
	
	@Autowired
	private PermissaoEstruturaService permissaoEstruturaService;
	
	@GetMapping("/permissao-estrutura")
	public Page<PermissaoEstrutura> get(@RequestParam(name="q") String query, Pageable pageable) throws Exception {
		PermissaoEstrutura permissaoEstrutura = new ObjectMapper().readValue(query, PermissaoEstrutura.class);
		return this.permissaoEstruturaService.find(permissaoEstrutura, pageable);
	}

	@GetMapping("/permissao-estrutura/{idPermissao}/{idEstrutura}")
	public ResponseEntity get(@PathVariable Long idPermissao, @PathVariable Long idEstrutura) throws Exception {
		PermissaoEstruturaPK id = new PermissaoEstruturaPK(idPermissao, idEstrutura);
		return ResponseEntity.status(HttpStatus.OK).body(this.permissaoEstruturaService.findOne(id));
	}

	@PostMapping("/permissao-estrutura")
	public ResponseEntity post(@RequestBody String body) throws Exception {
		PermissaoEstrutura permissaoEstrutura = new ObjectMapper().readValue(body, PermissaoEstrutura.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.permissaoEstruturaService.save(permissaoEstrutura));
	}

	@PutMapping("/permissao-estrutura")
	public ResponseEntity put(@RequestBody String body) throws Exception {
		PermissaoEstrutura permissaoEstrutura = new ObjectMapper().readValue(body, PermissaoEstrutura.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.permissaoEstruturaService.save(permissaoEstrutura));
	}

	@DeleteMapping("/permissao-estrutura/{idPermissao}/{idEstrutura}")
	public ResponseEntity delete(@PathVariable Long idPermissao, @PathVariable Long idEstrutura) throws Exception {
		PermissaoEstruturaPK id = new PermissaoEstruturaPK(idPermissao, idEstrutura);
		this.permissaoEstruturaService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}

}
