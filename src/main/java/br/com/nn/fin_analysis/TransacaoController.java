package br.com.nn.fin_analysis;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class TransacaoController {
	@GetMapping
	public ModelAndView getFormulario() {
		return new ModelAndView("importar-transacoes");
	}
	@PostMapping
	public ModelAndView postTransacao(@RequestParam("file") MultipartFile file) {
		System.out.println("Nome: " + file.getOriginalFilename());
		System.out.println("Tamanho: " + file.getSize()/1E6 + "MB"); 
		return new ModelAndView("importar-transacoes");
	}
}
