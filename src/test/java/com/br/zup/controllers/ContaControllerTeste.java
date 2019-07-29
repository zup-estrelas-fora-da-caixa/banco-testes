package com.br.zup.controllers;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.br.zup.models.Conta;
import com.br.zup.services.ContaService;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ContaController.class)
public class ContaControllerTeste {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ContaService contaService;
	
	@Test
	public void testarPegarContas() throws Exception {
		// Criar uma resposta esperada por .findAll()
		Conta conta = new Conta();
		
		conta.setId(1);
		conta.setBanco("6666 - Banco com Dinheiros");
		conta.setAgencia(4444);
		conta.setConta(11111);
		conta.setDigito(1);
		
		conta.setSaldoContaCorrente(100D);
		conta.setSaldoContaPoupanca(100D);
		
		List<Conta> contas = Arrays.asList(conta);
		
		given(contaService.findAll()).willReturn(contas);
		given(contaService.count()).willReturn(1L);
		
		this.mockMvc.perform(get("/api/conta"))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$", hasSize(1)))
						.andExpect(jsonPath("$[*].id").isNotEmpty());
	}
}
