package br.com.nn.fin_analysis.dto;

import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.nn.fin_analysis.model.Usuario;

public class UsuarioDto {
	@NotNull
	private Long id;
	@NotBlank
	private String nome;
	@Email
	@NotBlank
	private String email;
	
	public UsuarioDto() {
	}
	
	public UsuarioDto(Long id, String nome, String email) {
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
	}
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getEmail() {
		return email;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioDto other = (UsuarioDto) obj;
		return Objects.equals(id, other.id);
	}
	
}
