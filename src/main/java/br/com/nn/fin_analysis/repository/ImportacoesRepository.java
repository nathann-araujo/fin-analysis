package br.com.nn.fin_analysis.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.nn.fin_analysis.dto.DetalhesImportacaoDto;
import br.com.nn.fin_analysis.model.Importacao;

public interface ImportacoesRepository extends JpaRepository<Importacao, Long> {
	boolean existsByDataTransacao(LocalDate dataTransacao);
	
	@Query("Select new br.com.nn.fin_analysis.dto.DetalhesImportacaoDto(i.id, u.nome,"
			+ " i.dataTransacao, i.dataImportacao)"
			+ " from Importacao i INNER JOIN i.usuario u")
	List<DetalhesImportacaoDto> listarImportacoes();
	
	@Query("Select new br.com.nn.fin_analysis.dto.DetalhesImportacaoDto(i.id, u.nome,"
			+ " i.dataTransacao, i.dataImportacao)"
			+ " from Importacao i INNER JOIN i.usuario u"
			+ " where i.id = ?1")
	DetalhesImportacaoDto encontrarImportacaoComId(Long id);
	
}
