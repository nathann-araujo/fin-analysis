package br.com.nn.fin_analysis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

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
		try {
			InputStream inputStream = file.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			Stream<String> lines = bufferedReader.lines();
			lines.forEach(System.out::println);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("importar-transacoes");
	}
}
