package br.com.nn.fin_analysis.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.nn.fin_analysis.config.security.authentication.DetalhesDoUsuarioImpl;
import br.com.nn.fin_analysis.dto.UsuarioDto;
import br.com.nn.fin_analysis.dto.UsuarioForm;
import br.com.nn.fin_analysis.model.Usuario;
import br.com.nn.fin_analysis.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	public void registarUsuario(UsuarioForm novoUsuario) {
		String senha = this.gerarSenha();
		String senhaCriptografada =this.criptografarSenha(senha);
		Usuario usuario = new Usuario(novoUsuario.getNome(), novoUsuario.getEmail(), senhaCriptografada, true);
		usuarioRepository.save(usuario);
		this.enviarSenhaPeloEmail(senha, novoUsuario.getEmail());
	}
	private String gerarSenha() {
		Random rnd = new Random();
		int number = rnd.nextInt(999999);
		
		return String.format("%06d", number);
	}
	private void enviarSenhaPeloEmail(String senha, String email) {
		System.out.println("Deveria enviar email para: " + email);
		System.out.println("Deveria conter: " + senha);
	}
	
	private String criptografarSenha(String senha) {
		return encoder.encode(senha);
	}
	public List<UsuarioDto> getUsuarios() {
		return usuarioRepository.findByIdNot(1L);
		
	}
	public void deletarUsuario(Long id, DetalhesDoUsuarioImpl details) {
		if (id.equals(1L)) {
			System.out.println("NÃO DEVE DELETAR ADMIN");
			return;
		}
		
		if (!id.equals(details.getId())) {
			usuarioRepository.deleteById(id);
			return;
		}
		System.out.println("NÃO DEVE DELETAR A SI PRÓPRIO");
		
	}
	public void editarUsuario(DetalhesDoUsuarioImpl details, UsuarioDto usuarioForm) {
		Long id = usuarioForm.getId();
		if (id.equals(1L)) {
			System.out.println("NÃO DEVE EDITAR ADMIN");
			return;
		}
		Usuario usuario = usuarioRepository.getById(id);
		if (!usuarioForm.getNome().equals(usuario.getNome())) {
			usuario.setNome(usuarioForm.getNome());
		}
		if (!usuarioForm.getEmail().equals(usuario.getEmail())) {
			usuario.setEmail(usuarioForm.getEmail());
		}
		usuarioRepository.save(usuario);
	}
	public UsuarioDto getUsuario(Long id) {
		Usuario usuario = usuarioRepository.getById(id);
		return new UsuarioDto(usuario);
		
	}
}
