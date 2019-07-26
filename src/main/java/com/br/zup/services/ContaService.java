package com.br.zup.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.zup.models.Conta;
import com.br.zup.repositories.ContaRepository;

@Service
public class ContaService {
	@Autowired
	private ContaRepository contaRepository;
	
	public Iterable<Conta> findAll() {
		return contaRepository.findAll();
	}
	
	public long count() {
		return contaRepository.count();
	}
}
