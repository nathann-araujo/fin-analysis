package br.com.nn.fin_analysis.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nn.fin_analysis.model.Importacao;

public interface ImportacoesRepository extends JpaRepository<Importacao, Long> {
	boolean existsByDataTransacao(LocalDate dataTransacao);
}
