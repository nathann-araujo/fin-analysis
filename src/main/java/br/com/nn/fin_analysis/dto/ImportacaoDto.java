package br.com.nn.fin_analysis.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ImportacaoDto{

	private String nome;
	private LocalDate dataTransacao;
	private LocalDateTime dataImportacao;
	
	
	
	public ImportacaoDto(String nome, LocalDate dataTransacao, LocalDateTime dataImportacao) {
		this.nome = nome;
		this.dataTransacao = dataTransacao;
		this.dataImportacao = dataImportacao;
	}

	public String getNome() {
		return nome;
	}

	public LocalDate getDataTransacao() {
		return dataTransacao;
	}

	public LocalDateTime getDataImportacao() {
		return dataImportacao;
	}
	
}
