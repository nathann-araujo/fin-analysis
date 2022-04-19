package br.com.nn.fin_analysis.exception;

public class CsvValidationException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public CsvValidationException(String message) {
		super(message);
	}
	
}
