package br.com.nn.fin_analysis.dto;

import java.math.BigDecimal;

import br.com.nn.fin_analysis.model.Transacao;

public class TransacaoDto {
	private ContaDto contaOrigem;
	private ContaDto contaDestino;
	private BigDecimal valor;
	
	public TransacaoDto(ContaDto contaOrigem, ContaDto contaDestino, BigDecimal valor) {
		this.contaOrigem = contaOrigem;
		this.contaDestino = contaDestino;
		this.valor = valor;
	}
	public TransacaoDto(Transacao transacao) {
		this.contaOrigem = new ContaDto(transacao.getContaOrigem());
		this.contaDestino = new ContaDto(transacao.getContaDestino());
		this.valor = transacao.getValor();
	}

	public ContaDto getContaOrigem() {
		return contaOrigem;
	}

	public ContaDto getContaDestino() {
		return contaDestino;
	}

	public BigDecimal getValor() {
		return valor;
	}
	
	
	
}
