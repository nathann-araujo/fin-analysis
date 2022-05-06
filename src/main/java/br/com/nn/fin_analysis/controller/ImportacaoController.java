package br.com.nn.fin_analysis.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.nn.fin_analysis.config.security.authentication.DetalhesDoUsuarioImpl;
import br.com.nn.fin_analysis.dto.ImportacaoDto;
import br.com.nn.fin_analysis.exception.CsvValidationException;
import br.com.nn.fin_analysis.service.TransacaoService;

@Controller
@RequestMapping("/importacoes")
public class ImportacaoController {
	
	@Autowired
	TransacaoService transacaoService;
	
	@GetMapping
	public ModelAndView getFormulario() {
		ModelAndView mv = new ModelAndView("importar-transacoes");
		mv.addObject("listaImportacoes", transacaoService.getImportacoes());
		return mv;
	}
	@PostMapping
	public ModelAndView postTransacao(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes,
			@AuthenticationPrincipal DetalhesDoUsuarioImpl details) {
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "O arquivo importado está vazio! Use um arquivo válido!");
			return new ModelAndView("redirect:/importacoes");
		}
		try {
			InputStream inputStream = file.getInputStream();
			Scanner scanner = new Scanner(inputStream);
			this.transacaoService.registrar(scanner, details);
			scanner.close();
		} catch (IOException | CsvValidationException e) {
			if(e instanceof CsvValidationException) {
				redirectAttributes.addFlashAttribute("message",e.getMessage());
				return new ModelAndView("redirect:/importacoes");
			}
		}
		
		redirectAttributes.addFlashAttribute("message", "Arquivo adicionado com sucesso!");
		return new ModelAndView("redirect:/importacoes");
	}
	@GetMapping("/detalhar/{id}")
	public ModelAndView getDetalhes(@PathVariable Long id) {
		ImportacaoDto importacao = transacaoService.getImportacao(id);
		ModelAndView mv = new ModelAndView("importacao-detalhes");
		mv.addObject("importacao", importacao);
		return mv;
	}
}
