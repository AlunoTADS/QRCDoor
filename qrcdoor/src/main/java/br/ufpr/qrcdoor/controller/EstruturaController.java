package br.ufpr.qrcdoor.controller;

import static br.ufpr.qrcdoor.interceptor.WebSocketSessionCapturingHandlerDecorator.estruturasConectadas;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufpr.qrcdoor.entity.Estrutura;
import br.ufpr.qrcdoor.service.EstruturaService;

@SuppressWarnings("rawtypes")
@RestController
public class EstruturaController {
	
	@Autowired
	private EstruturaService estruturaService;
	
	@GetMapping("/estrutura/status/{id}")
	public ResponseEntity status(@PathVariable String id) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(estruturasConectadas.containsKey(id));
	}
	
	@GetMapping("/estrutura")
	@ResponseBody
	public Page<Estrutura> get(@RequestParam(name="q") String query, Pageable pageable) throws Exception {
		Estrutura estrutura = new ObjectMapper().readValue(query, Estrutura.class);
		return this.estruturaService.find(estrutura, pageable);
	}

	@GetMapping("/estrutura/{id}")
	public ResponseEntity get(@PathVariable Long id) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.estruturaService.findOne(id));
	}
	
	@GetMapping("/estrutura/foto/{id}")
	public ResponseEntity foto(@PathVariable Long id) throws Exception {
		Estrutura estrutura = this.estruturaService.findOne(id);
		MediaType mediaType;
		byte[] foto;
		if (estrutura.getFoto() != null) {
			mediaType = (estrutura.getFotoExtensao().equals("png")) ? MediaType.IMAGE_PNG : MediaType.IMAGE_JPEG;
			foto = estrutura.getFoto();
		} else {
			InputStream inputStream = EstruturaController.class.getClassLoader().getResourceAsStream("img/lock.png");
			foto = IOUtils.toByteArray(inputStream);
			inputStream.close();
			mediaType = MediaType.IMAGE_PNG;
		}
		return ResponseEntity.status(HttpStatus.OK).contentType(mediaType).body(foto);
	}
	
	@PutMapping("/estrutura/foto/{id}")
	public ResponseEntity foto(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws Exception {
		Estrutura estrutura = this.estruturaService.findOne(id);
		estrutura.setFoto(file.getBytes());
		
		String name = file.getOriginalFilename();
		estrutura.setFotoExtensao(name.substring(name.lastIndexOf(".") + 1));
		
		this.estruturaService.save(estrutura);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}

	@PostMapping("/estrutura")
	public ResponseEntity post(@RequestBody String body) throws Exception {
		Estrutura estrutura = new ObjectMapper().readValue(body, Estrutura.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.estruturaService.save(estrutura));
	}

	@PutMapping("/estrutura")
	public ResponseEntity put(@RequestBody String body) throws Exception {
		Estrutura estrutura = new ObjectMapper().readValue(body, Estrutura.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.estruturaService.save(estrutura));
	}

	@DeleteMapping("/estrutura/{id}")
	public ResponseEntity delete(@PathVariable Long id) throws Exception {
		this.estruturaService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}

}
