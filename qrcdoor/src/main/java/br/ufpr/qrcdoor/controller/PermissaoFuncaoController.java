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

import br.ufpr.qrcdoor.entity.PermissaoFuncao;
import br.ufpr.qrcdoor.service.PermissaoFuncaoService;

@SuppressWarnings("rawtypes")
@RestController
public class PermissaoFuncaoController {
	
	@Autowired
	private PermissaoFuncaoService permissaoFuncaoService;
	
	@GetMapping("/permissaoFuncoes")
	public ResponseEntity get() throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.permissaoFuncaoService.findAll());
	}

	@GetMapping("/permissaoFuncao/{id}")
	public ResponseEntity get(@PathVariable Long id) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.permissaoFuncaoService.findOne(id));
	}

	@PostMapping("/permissaoFuncoes")
	public ResponseEntity post(@RequestBody String body) throws Exception {
		PermissaoFuncao permissaoFuncao = new ObjectMapper().readValue(body, PermissaoFuncao.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.permissaoFuncaoService.save(permissaoFuncao));
	}

	@PutMapping("/permissaoFuncoes")
	public ResponseEntity put(@RequestBody String body) throws Exception {
		PermissaoFuncao permissaoFuncao = new ObjectMapper().readValue(body, PermissaoFuncao.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.permissaoFuncaoService.save(permissaoFuncao));
	}

	@DeleteMapping("/permissaoFuncoes/{id}")
	public ResponseEntity delete(@PathVariable Long id) throws Exception {
		this.permissaoFuncaoService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}

}
