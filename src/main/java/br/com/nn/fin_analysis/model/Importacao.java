package br.com.nn.fin_analysis.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Importacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private LocalDate dataTransacao;
	private LocalDateTime dataImportacao;
	@Column(name = "usuario_id")
	private Long usuarioId;
	
	@ManyToOne(targetEntity = Usuario.class,fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id",insertable = false, updatable = false)
	private Usuario usuario;
	@OneToMany(mappedBy = "importacao")
	private List<Transacao> transacoes;
	
	
	
	public Importacao() {
	}
	public Importacao(LocalDate dataTransacao, LocalDateTime dataImportacao, Long usuarioId) {
		this.dataTransacao = dataTransacao;
		this.dataImportacao = dataImportacao;
		this.usuarioId = usuarioId;
	}


	public Long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LocalDate getDataTransacao() {
		return dataTransacao;
	}
	public void setDataTransacao(LocalDate dataTransacao) {
		this.dataTransacao = dataTransacao;
	}
	public LocalDateTime getDataImportacao() {
		return dataImportacao;
	}
	public void setDataImportacao(LocalDateTime dataImportacao) {
		this.dataImportacao = dataImportacao;
	}
	public Long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	public List<Transacao> getTransacoes() {
		return Collections.unmodifiableList(transacoes);
	}
	public void setTransacoes(List<Transacao> transacoes) {
		this.transacoes = transacoes;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	
}
