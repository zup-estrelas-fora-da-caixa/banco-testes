package com.br.zup.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.zup.models.Conta;
import com.br.zup.services.ContaService;

@RestController
@RequestMapping("/api/conta")
public class ContaController {
	@Autowired
	private ContaService contaService;
	
	@GetMapping
	public ResponseEntity<?> pegarContas() {
		if(contaService.count() == 0) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(contaService.findAll());
	}
	
	@PostMapping
	public ResponseEntity<?> criarConta(@Valid @RequestBody Conta conta) {
		contaService.save(conta);
		return ResponseEntity.status(HttpStatus.CREATED).body(conta);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> apagarConta(@PathVariable int id) {
		contaService.deleteById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
}
