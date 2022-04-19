package br.com.nn.fin_analysis.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public void registrar(Scanner scanner) {
		String primeiraTransacao = scanner.nextLine();
		String[] detalhesPrimeiraTransacao = primeiraTransacao.split(",");
		LocalDate diaDaTransacao = this.determinarDiaDaTransacao(detalhesPrimeiraTransacao);
		boolean transacaoEhValida = this.validarTransacao(detalhesPrimeiraTransacao, diaDaTransacao);
		if (transacaoEhValida) {
			this.salvarTransacao(detalhesPrimeiraTransacao);
		} else {
			throw new CsvValidationException("Transação Inválida!");
		}
		
		while(scanner.hasNextLine()) {
			String[] detalhesTransacao = scanner.nextLine().replaceAll("(,\\s*,){1,}", ",").split(",");
			if(this.validarTransacao(detalhesTransacao, diaDaTransacao)) {
				this.salvarTransacao(detalhesTransacao);
			}
		}
		Importacao importacao = new Importacao(diaDaTransacao, LocalDateTime.now());
		importacoesRepository.save(importacao);
	}
	
	private void salvarTransacao(String[] detalhesTransacao) {
		Conta contaOrigem = new Conta(detalhesTransacao[0],detalhesTransacao[1],detalhesTransacao[2]);
		Conta contaDestino = new Conta(detalhesTransacao[3],detalhesTransacao[4],detalhesTransacao[5]);
		
		Transacao transacao = new Transacao(contaOrigem,
				contaDestino,
				new BigDecimal(detalhesTransacao[6]),
				LocalDateTime.parse(detalhesTransacao[7]));
		transacaoRepository.save(transacao);
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
	
	public List<Importacao> getImportacoes() {
		return this.importacoesRepository.findAll();
	}

}
