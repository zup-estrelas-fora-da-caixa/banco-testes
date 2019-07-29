package com.br.zup.controllers;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.br.zup.models.Conta;
import com.br.zup.services.ContaService;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	private Conta conta;
	
	@Before
	public void preparar() {
		// Criar uma resposta esperada por .findAll()
				conta = new Conta();
				
				conta.setId(1);
				conta.setBanco("6666 - Banco com Dinheiros");
				conta.setAgencia(4444);
				conta.setConta(11111);
				conta.setDigito(1);
				
				conta.setSaldoContaCorrente(100D);
				conta.setSaldoContaPoupanca(100D);
	}
	
	@Test
	public void testarPegarContas() throws Exception {
		List<Conta> contas = Arrays.asList(conta);
		
		given(contaService.findAll()).willReturn(contas);
		given(contaService.count()).willReturn(1L);
		
		this.mockMvc.perform(get("/api/conta"))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$", hasSize(1)))
						.andExpect(jsonPath("$[*].id").isNotEmpty());
	}
	
	@Test
	public void testarPegarContasErro() throws Exception {
		given(contaService.count()).willReturn(0L);
		
		this.mockMvc.perform(get("/api/conta"))
						.andExpect(status().isNoContent());
	}
	
	@Test
	public void testarCriarConta() throws Exception {
		conta.setId(10);
		given(contaService.save(conta)).willReturn(conta);
		
		this.mockMvc.perform(
				post("/api/conta")
					.content(transformarEmJson(conta))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value(10));
	}
	
	@Test
	public void testarApagarConta() throws Exception {
		this.mockMvc.perform(delete("/api/conta/{id}", 2))
						.andExpect(status().isAccepted());
	}
	
	public static String transformarEmJson(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
