package com.br.zup.models;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ContaTeste {
	private Conta conta;
	
	@Before
	public void preparar() {
		conta = new Conta();
		
		conta.setId(null);
		conta.setBanco("6666 - Banco com Dinheiros");
		conta.setAgencia(4444);
		conta.setConta(11111);
		conta.setDigito(1);
		
		conta.setSaldoContaCorrente(100D);
		conta.setSaldoContaPoupanca(100D);
	}
	
	@Test
	public void testarCriacaoConta() {
		assertEquals("6666 - Banco com Dinheiros", conta.getBanco());
	}
	
	@Test
	public void testarDepositarContaCorrente() {
		conta.depositarContaCorrente(100D);
		assertEquals(200D, conta.getSaldoContaCorrente(), 0D);
	}
	
	@Test
	public void testarDepositarContaPoupanca() {
		conta.depositarContaPoupanca(100D);
		assertEquals(200D, conta.getSaldoContaPoupanca(), 0D);
	}
	
	@Test
	public void testarSacarContaCorrente() {
		double valorSacado = conta.sacarContaCorrente(100D);
		assertEquals(0D, conta.getSaldoContaCorrente(), 0D);
		assertEquals(100D, valorSacado, 0D);
	}
	
	@Test
	public void testarSacarContaPoupanca() {
		double valorSacado = conta.sacarContaPoupanca(100D);
		assertEquals(0D, conta.getSaldoContaPoupanca(), 0D);
		assertEquals(100D, valorSacado, 0D);
	}
	
	@Test
	public void testarTransferirCorrenteParaPoupanca() {
		conta.transferirCorrenteParaPoupanca(100D);
		assertEquals(0D, conta.getSaldoContaCorrente(), 0D);
		assertEquals(200D, conta.getSaldoContaPoupanca(), 0D);
	}
	
	@Test
	public void testarTransferirPoupancaParaCorrente() {
		conta.transferirPoupancaParaCorrente(100D);
		assertEquals(0D, conta.getSaldoContaPoupanca(), 0D);
		assertEquals(200D, conta.getSaldoContaCorrente(), 0D);
	}
}
