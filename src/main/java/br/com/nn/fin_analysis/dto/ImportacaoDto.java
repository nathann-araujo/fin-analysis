package br.com.nn.fin_analysis.dto;

import java.util.Collections;
import java.util.List;

public class ImportacaoDto {
	private DetalhesImportacaoDto detalhes;
	private List<TransacaoDto> transacoes;
	
	
	public ImportacaoDto(DetalhesImportacaoDto detalhes, List<TransacaoDto> transacoes) {
		this.detalhes = detalhes;
		this.transacoes = transacoes;
	}
	
	public DetalhesImportacaoDto getDetalhes() {
		return detalhes;
	}

	public List<TransacaoDto> getTransacoes() {
		return Collections.unmodifiableList(transacoes);
	}
	
	
}
