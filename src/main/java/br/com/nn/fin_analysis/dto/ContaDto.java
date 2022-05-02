package br.com.nn.fin_analysis.dto;

import br.com.nn.fin_analysis.model.Conta;

public class ContaDto {
	private String banco;
	private String agencia;
	private String conta;
	
	public ContaDto(String banco, String agencia, String conta) {
		this.banco = banco;
		this.agencia = agencia;
		this.conta = conta;
	}
	
	public ContaDto(Conta conta) {
		this.banco = conta.getBanco();
		this.agencia = conta.getAgencia();
		this.conta = conta.getNumeroConta();
	}
	
	public String getBanco() {
		return banco;
	}
	public String getAgencia() {
		return agencia;
	}
	public String getConta() {
		return conta;
	}
	
	
}
