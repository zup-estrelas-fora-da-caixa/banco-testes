package com.br.zup.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ContaTeste {
	private Conta conta;
	
	@Test
	public void testarDepositarContaCorrente() {
		conta = new Conta();
		
		conta.setId(null);
		conta.setBanco("6666 - Banco com Dinheiros");
		conta.setAgencia(4444);
		conta.setConta(11111);
		conta.setDigito(1);
		
		conta.setSaldoContaCorrente(100D);
		conta.setSaldoContaPoupanca(0D);
		
		conta.depositarContaCorrente(100D);
		assertEquals(200D, conta.getSaldoContaCorrente(), 0D);
	}
}
