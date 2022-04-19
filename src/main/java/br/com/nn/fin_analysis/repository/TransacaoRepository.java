package br.com.nn.fin_analysis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nn.fin_analysis.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long>{

}
