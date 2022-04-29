package br.com.nn.fin_analysis.config.security.authentication;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.nn.fin_analysis.model.Usuario;

public class DetalhesDoUsuarioImpl implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	private final Long id;
	private final String username;
	private final String password;
	private final boolean enabled;
	private final List<GrantedAuthority> rolesAndAuthorities;
	
	public DetalhesDoUsuarioImpl(Usuario usuario) {
		this.id = usuario.getId();
		this.username = usuario.getEmail();
		this.password = usuario.getSenha();
		this.enabled = usuario.isAtivo();
		this.rolesAndAuthorities = List.of();
	}
	
	
	
	public Long getId() {
		return id;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return rolesAndAuthorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

}
