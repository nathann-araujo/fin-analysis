package br.com.nn.fin_analysis.config.security.authentication;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.nn.fin_analysis.model.Usuario;
import br.com.nn.fin_analysis.repository.UsuarioRepository;

@Service
public class DetalhesDoUsuarioService implements UserDetailsService{

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
		usuario.orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));
		return usuario.map(DetalhesDoUsuarioImpl::new).get();
	}

}
