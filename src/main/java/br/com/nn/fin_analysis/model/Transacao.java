package br.com.nn.fin_analysis.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Positive;

@Entity
public class Transacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Embedded
	@AttributeOverrides({
		  @AttributeOverride( name = "banco", column = @Column(name = "banco_origem")),
		  @AttributeOverride( name = "agencia", column = @Column(name = "agencia_origem")),
		  @AttributeOverride( name = "numeroConta", column = @Column(name = "conta_origem"))
		})
	private Conta contaOrigem;
	@Embedded
	@AttributeOverrides({
		  @AttributeOverride( name = "banco", column = @Column(name = "banco_destino")),
		  @AttributeOverride( name = "agencia", column = @Column(name = "agencia_destino")),
		  @AttributeOverride( name = "numeroConta", column = @Column(name = "conta_destino"))
		})
	private Conta contaDestino;
	@Positive
	private BigDecimal valor;
	private LocalDateTime horario;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "importacao_id")
	private Importacao importacao;
	
	public Transacao() {
		// TODO Auto-generated constructor stub
	}

	
	public Transacao(Conta contaOrigem, Conta contaDestino,
			@Positive BigDecimal valor, LocalDateTime horario, Importacao importacao) {
		this.contaOrigem = contaOrigem;
		this.contaDestino = contaDestino;
		this.valor = valor;
		this.horario = horario;
		this.importacao = importacao;
	}


	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Conta getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(Conta contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public Conta getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(Conta contaDestino) {
		this.contaDestino = contaDestino;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDateTime getHorario() {
		return horario;
	}

	public void setHorario(LocalDateTime horario) {
		this.horario = horario;
	}
	public Importacao getImportacao() {
		return importacao;
	}
	public void setImportacao(Importacao importacao) {
		this.importacao = importacao;
	}
}
