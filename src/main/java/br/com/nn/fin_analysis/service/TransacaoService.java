package br.com.nn.fin_analysis.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nn.fin_analysis.config.security.authentication.DetalhesDoUsuarioImpl;
import br.com.nn.fin_analysis.dto.DetalhesImportacaoDto;
import br.com.nn.fin_analysis.dto.ImportacaoDto;
import br.com.nn.fin_analysis.dto.TransacaoDto;
import br.com.nn.fin_analysis.exception.CsvValidationException;
import br.com.nn.fin_analysis.model.Conta;
import br.com.nn.fin_analysis.model.Importacao;
import br.com.nn.fin_analysis.model.Transacao;
import br.com.nn.fin_analysis.repository.ImportacoesRepository;
import br.com.nn.fin_analysis.repository.TransacaoRepository;

@Service
public class TransacaoService {
	
	@Autowired
	ImportacoesRepository importacoesRepository;
	
	@Autowired
	TransacaoRepository transacaoRepository;
	
	@Transactional
	public void registrar(Scanner scanner, DetalhesDoUsuarioImpl details) {
		List<Transacao> listaDeTransacoes = new ArrayList<>();
		String primeiraTransacao = scanner.nextLine();
		String[] detalhesPrimeiraTransacao = primeiraTransacao.split(",");
		LocalDate diaDaTransacao = this.determinarDiaDaTransacao(detalhesPrimeiraTransacao);
		Importacao importacao = new Importacao(diaDaTransacao, LocalDateTime.now(), details.getId());
		boolean transacaoEhValida = this.validarTransacao(detalhesPrimeiraTransacao, diaDaTransacao);
		if (transacaoEhValida) {
			Transacao transacao = this.obterTransacao(detalhesPrimeiraTransacao, importacao);
			listaDeTransacoes.add(transacao);
			
		} else {
			throw new CsvValidationException("Transação Inválida!");
		}
		
		while(scanner.hasNextLine()) {
			String[] detalhesTransacao = scanner.nextLine().replaceAll("(,\\s*,){1,}", ",").split(",");
			if(this.validarTransacao(detalhesTransacao, diaDaTransacao)) {
				Transacao transacao = this.obterTransacao(detalhesTransacao, importacao);
				listaDeTransacoes.add(transacao);
			}
		}
		importacoesRepository.save(importacao);
		transacaoRepository.saveAll(listaDeTransacoes);
		
	}
	
	private Transacao obterTransacao(String[] detalhesTransacao, Importacao importacao) {
		Conta contaOrigem = new Conta(detalhesTransacao[0],detalhesTransacao[1],detalhesTransacao[2]);
		Conta contaDestino = new Conta(detalhesTransacao[3],detalhesTransacao[4],detalhesTransacao[5]);
		
		Transacao transacao = new Transacao(contaOrigem,
				contaDestino,
				new BigDecimal(detalhesTransacao[6]),
				LocalDateTime.parse(detalhesTransacao[7])
				, importacao);
		return transacao;
	}
	
	private boolean validarTransacao(String[] detalhesTransacao, LocalDate diaDaTransacao) {
		if (detalhesTransacao.length != 8) {
			return false;
		}
		return LocalDateTime.parse(detalhesTransacao[7]).toLocalDate().isEqual(diaDaTransacao);
		
	}
	

	private LocalDate determinarDiaDaTransacao(String[] primeiraTransacao) {
		if (primeiraTransacao.length != 8) {
			throw new CsvValidationException("A primeira transação do arquivo é inválida!");
		}
		LocalDate diaTransacao;
		try {
			diaTransacao = LocalDateTime.parse(primeiraTransacao[7]).toLocalDate();
		} catch (Exception e) {
			throw new CsvValidationException("Data inválida!");
		}
		if (importacoesRepository.existsByDataTransacao(diaTransacao)) {
			throw new CsvValidationException("Data inválida,"
					+ " as transações para essa data já foram importadas!");
		}
		return diaTransacao;
	}
	
	public List<DetalhesImportacaoDto> getImportacoes() {
		List<DetalhesImportacaoDto> lista = this.importacoesRepository.listarImportacoes();
		return lista;
	}

	public ImportacaoDto getImportacao(Long id) {
		DetalhesImportacaoDto detalhesImportacaoDto = this.importacoesRepository.encontrarImportacaoComId(id);
		List<TransacaoDto> transacoes = this.transacaoRepository.findByImportacaoId(id);
		return new ImportacaoDto(detalhesImportacaoDto, transacoes);
	}

}
