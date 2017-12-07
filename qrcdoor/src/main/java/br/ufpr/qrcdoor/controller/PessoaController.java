package br.ufpr.qrcdoor.controller;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
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

@SuppressWarnings("rawtypes")
@RestController
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping("/pessoa")
	public Page<Pessoa> get(@RequestParam(name="q") String query, Pageable pageable) throws Exception {
		Pessoa pessoa = new ObjectMapper().readValue(query, Pessoa.class);
		return this.pessoaService.find(pessoa, pageable);
	}

	@GetMapping("/pessoa/{id}")
	public ResponseEntity get(@PathVariable Long id) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.pessoaService.findOne(id));
	}
	
	@GetMapping("/pessoa/foto/{id}")
	public ResponseEntity foto(@PathVariable Long id) throws Exception {
		Pessoa pessoa = this.pessoaService.findOne(id);
		MediaType mediaType;
		byte[] foto;
		if (pessoa.getFoto() != null) {
			mediaType = (pessoa.getFotoExtensao().equals("png")) ? MediaType.IMAGE_PNG : MediaType.IMAGE_JPEG;
			foto = pessoa.getFoto();
		} else {
			InputStream inputStream = PessoaController.class.getClassLoader().getResourceAsStream("img/profile.png");
			foto = IOUtils.toByteArray(inputStream);
			inputStream.close();
			mediaType = MediaType.IMAGE_PNG;
		}
		return ResponseEntity.status(HttpStatus.OK).contentType(mediaType).body(foto);
	}
	
	@PutMapping("/pessoa/foto/{id}")
	public ResponseEntity foto(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws Exception {
		Pessoa pessoa = this.pessoaService.findOne(id);
		pessoa.setFoto(file.getBytes());
		
		String name = file.getOriginalFilename();
		pessoa.setFotoExtensao(name.substring(name.lastIndexOf(".") + 1));
		
		this.pessoaService.update(pessoa);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}
	
	@PostMapping("/pessoa")
	public ResponseEntity post(@RequestBody String body) throws Exception {
		Pessoa pessoa = new ObjectMapper().readValue(body, Pessoa.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.pessoaService.insert(pessoa));
	}

	@PutMapping("/pessoa")
	public ResponseEntity put(@RequestBody String body) throws Exception {
		Pessoa pessoa = new ObjectMapper().readValue(body, Pessoa.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.pessoaService.update(pessoa));
	}

	@DeleteMapping("/pessoa/{id}")
	public ResponseEntity delete(@PathVariable Long id) throws Exception {
		this.pessoaService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}

}
