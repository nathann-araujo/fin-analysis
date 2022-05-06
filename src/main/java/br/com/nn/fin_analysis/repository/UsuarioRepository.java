package br.com.nn.fin_analysis.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nn.fin_analysis.dto.UsuarioDto;
import br.com.nn.fin_analysis.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findByEmail(String email);

	List<UsuarioDto> findByIdNot(Long id);
}
