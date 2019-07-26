package com.br.zup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.zup.services.ContaService;

@RestController
@RequestMapping("/api/conta")
public class ContaController {
	@Autowired
	private ContaService contaService;
	
	@GetMapping
	public ResponseEntity<?> pegarContas() {
		if( contaService.count() == 0) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(contaService.findAll());
	}
}
