package br.com.nn.fin_analysis.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioForm {
	@NotBlank
	private String nome;
	@Email
	@NotBlank
	private String email;
	
	public UsuarioForm() {
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
