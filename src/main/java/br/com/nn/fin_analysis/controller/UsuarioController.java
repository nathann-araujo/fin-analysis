package br.com.nn.fin_analysis.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.nn.fin_analysis.config.security.authentication.DetalhesDoUsuarioImpl;
import br.com.nn.fin_analysis.dto.UsuarioDto;
import br.com.nn.fin_analysis.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping
	public ModelAndView getUsuarios(@AuthenticationPrincipal DetalhesDoUsuarioImpl details) {
		ModelAndView mv = new ModelAndView("usuarios");
		List<UsuarioDto> usuarios = usuarioService.getUsuarios();
		
		mv.addObject("listaDeUsuarios", usuarios);
		mv.addObject("username", details.getUsername());
		return mv;
	}
	@PostMapping("/deletar")
	public ModelAndView deletarUsuario(@RequestParam Long id,
			@AuthenticationPrincipal DetalhesDoUsuarioImpl details) {
		usuarioService.deletarUsuario(id, details);
		ModelAndView mv = new ModelAndView("redirect:/usuarios");
		return mv;
	}
	@GetMapping("/editar/{id}")
	public ModelAndView getFormulario(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("editar-usuario");
		UsuarioDto usuarioDto = usuarioService.getUsuario(id);
		mv.addObject("usuarioDto",usuarioDto);
		return mv;
	}

	@PostMapping("/editar/{id}")
	public ModelAndView cadastrarUsuario(@Valid @ModelAttribute UsuarioDto usuarioDto,
			Errors erros, @AuthenticationPrincipal DetalhesDoUsuarioImpl details) {
		
		ModelAndView mv = new ModelAndView("editar-usuario");
		mv.addObject("usuarioDto", usuarioDto);
		
		if (erros.hasErrors()) {
			return mv;
		}
	
		try {
			usuarioService.editarUsuario(details, usuarioDto);
		} catch (DataIntegrityViolationException e) {
			erros.rejectValue("email", "constraint.email", "email existente");
			return mv;
		}
		
		return new ModelAndView("redirect:/usuarios");
	}
	
}
