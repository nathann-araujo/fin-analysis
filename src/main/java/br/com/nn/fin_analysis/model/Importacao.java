package br.com.nn.fin_analysis.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Importacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(unique = true)
	private LocalDate dataTransacao;
	private LocalDateTime dataImportacao;
	public Importacao() {
		super();
	}
	
	
	public Importacao(LocalDate dataTransacao, LocalDateTime dataImportacao) {
		this.dataTransacao = dataTransacao;
		this.dataImportacao = dataImportacao;
	}


	public long getId() {
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
	
	
}
