package br.ufpr.qrcdoor.controller;

import java.util.Map;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufpr.qrcdoor.entity.Pessoa;
import br.ufpr.qrcdoor.service.PessoaService;
import br.ufpr.qrcdoor.util.Util;

@SuppressWarnings("rawtypes")
@RestController
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping("/pessoas")
	public Page<Pessoa> get(@RequestParam Map<String, String> query, Pageable pageable) throws Exception {
		query = Util.cleanQueryMap(query);
		Pessoa pessoa = new ObjectMapper().readValue(new ObjectMapper().writeValueAsString(query), Pessoa.class);
		return this.pessoaService.find(pessoa, pageable);
	}

	@GetMapping("/pessoa/{id}")
	public ResponseEntity get(@PathVariable Long id) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.pessoaService.findOne(id));
	}
	
	@GetMapping("/pessoa/foto/{id}")
	public ResponseEntity foto(@PathVariable Long id) throws Exception {
		Pessoa pessoa = this.pessoaService.findOne(id);
		MediaType mediaType = (pessoa.getFotoExtensao().equals("png")) ? MediaType.IMAGE_PNG : MediaType.IMAGE_JPEG;
		return ResponseEntity.status(HttpStatus.OK).contentType(mediaType).body(pessoa.getFoto());
	}
	
	@PutMapping("/pessoa/foto/{id}")
	public ResponseEntity foto(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws Exception {
		Pessoa pessoa = this.pessoaService.findOne(id);
		pessoa.setFoto(file.getBytes());
		this.pessoaService.update(pessoa);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@PostMapping("/pessoas")
	public ResponseEntity post(@RequestBody String body) throws Exception {
		Pessoa pessoa = new ObjectMapper().readValue(body, Pessoa.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.pessoaService.insert(pessoa));
	}

	@PutMapping("/pessoas")
	public ResponseEntity put(@RequestBody String body) throws Exception {
		Pessoa pessoa = new ObjectMapper().readValue(body, Pessoa.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.pessoaService.update(pessoa));
	}

	@DeleteMapping("/pessoas/{id}")
	public ResponseEntity delete(@PathVariable Long id) throws Exception {
		this.pessoaService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}

}
