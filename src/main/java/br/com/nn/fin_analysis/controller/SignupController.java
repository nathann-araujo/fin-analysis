package br.com.nn.fin_analysis.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.nn.fin_analysis.dto.UsuarioForm;
import br.com.nn.fin_analysis.service.EmailService;
import br.com.nn.fin_analysis.service.UsuarioService;

@Controller
@RequestMapping("/signup")
public class SignupController {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	EmailService emailService;
	
	@GetMapping
	public ModelAndView getFormulario() {
		ModelAndView mv = new ModelAndView("cadastrar-usuario");
		mv.addObject("usuarioForm", new UsuarioForm());
		return mv;
	}

	@PostMapping
	public ModelAndView cadastrarUsuario(@Valid @ModelAttribute UsuarioForm novoUsuario,
			Errors erros) {
		
		ModelAndView mv = new ModelAndView("cadastrar-usuario");
		mv.addObject("usuarioForm", novoUsuario);
		
		if (erros.hasErrors()) {
			return mv;
		}
	
		try {
			usuarioService.registarUsuario(novoUsuario);
		} catch (DataIntegrityViolationException e) {
			erros.rejectValue("email", "constraint.email", "email existente");
			return mv;
		}
		
		return new ModelAndView("redirect:/signup");
	}

}
