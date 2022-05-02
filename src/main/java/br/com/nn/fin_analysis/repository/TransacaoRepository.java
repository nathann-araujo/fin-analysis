package br.com.nn.fin_analysis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.nn.fin_analysis.dto.TransacaoDto;
import br.com.nn.fin_analysis.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long>{
	@Query("Select t FROM "
			+ "Transacao t INNER JOIN t.importacao i INNER JOIN i.usuario "
			+ "where i.id=?1")
	List<Transacao> encontrarComImportacaoId(Long importacaoId);
	List<TransacaoDto> findByImportacaoId(Long id);
}
