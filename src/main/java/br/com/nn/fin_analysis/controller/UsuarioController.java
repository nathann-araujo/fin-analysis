package br.com.nn.fin_analysis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ModelAndView getFormulario(@AuthenticationPrincipal DetalhesDoUsuarioImpl details) {
		ModelAndView mv = new ModelAndView("usuarios");
		List<UsuarioDto> usuarios = usuarioService.getUsuarios();
		
		mv.addObject("listaDeUsuarios", usuarios);
		mv.addObject("username", details.getUsername());
		return mv;
	}
}
