package br.com.nn.fin_analysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.nn.fin_analysis.service.UsuarioService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping
	public ModelAndView getFormulario() {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}


}
