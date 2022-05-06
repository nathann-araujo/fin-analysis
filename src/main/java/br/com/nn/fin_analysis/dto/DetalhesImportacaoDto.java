package br.com.nn.fin_analysis.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DetalhesImportacaoDto{
	private Long id;
	private String nome;
	private LocalDate dataTransacao;
	private LocalDateTime dataImportacao;
	
	
	
	public DetalhesImportacaoDto(Long id, String nome, LocalDate dataTransacao, LocalDateTime dataImportacao) {
		this.id = id;
		this.nome = nome;
		this.dataTransacao = dataTransacao;
		this.dataImportacao = dataImportacao;
	}
	

	public Long getId() {
		return id;
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
