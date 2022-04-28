package br.com.nn.fin_analysis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nn.fin_analysis.dto.UsuarioDto;
import br.com.nn.fin_analysis.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	List<UsuarioDto> findByIdNot(Long id);
}
