package br.ufpr.qrcdoor.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@SuppressWarnings("rawtypes")
@RestController
public class LoginController {
	
	@PostMapping("/login")
	public ResponseEntity post(@RequestBody String body) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body("");
	}
}
