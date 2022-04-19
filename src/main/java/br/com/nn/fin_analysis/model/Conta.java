package br.com.nn.fin_analysis.model;

import javax.persistence.Embeddable;

@Embeddable
public class Conta {
	private String banco;
	private String agencia;
	private String numeroConta;
	
	public Conta() {}
	
	public Conta(String banco, String agencia, String numeroConta) {
		this.banco = banco;
		this.agencia = agencia;
		this.numeroConta = numeroConta;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getNumeroConta() {
		return numeroConta;
	}
	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}
	
	
}
