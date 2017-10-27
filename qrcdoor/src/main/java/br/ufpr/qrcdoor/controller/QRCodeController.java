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

import br.ufpr.qrcdoor.entity.QRCode;
import br.ufpr.qrcdoor.service.QRCodeService;

@SuppressWarnings("rawtypes")
@RestController
public class QRCodeController {
	
	@Autowired
	private QRCodeService qrCodeService;
	
	@GetMapping("/qrcodes")
	public ResponseEntity get() throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.qrCodeService.findAll());
	}

	@GetMapping("/qrcode/{id}")
	public ResponseEntity get(@PathVariable Long id) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.qrCodeService.findOne(id));
	}

	@PostMapping("/qrcodes")
	public ResponseEntity post(@RequestBody String body) throws Exception {
		QRCode qrCode = new ObjectMapper().readValue(body, QRCode.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.qrCodeService.save(qrCode));
	}

	@PutMapping("/qrcodes")
	public ResponseEntity put(@RequestBody String body) throws Exception {
		QRCode qrCode = new ObjectMapper().readValue(body, QRCode.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.qrCodeService.save(qrCode));
	}

	@DeleteMapping("/qrcodes/{id}")
	public ResponseEntity delete(@PathVariable Long id) throws Exception {
		this.qrCodeService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}

}
