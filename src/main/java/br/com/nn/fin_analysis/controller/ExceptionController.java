package br.com.nn.fin_analysis.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ModelAndView handle(HttpServletRequest req, Exception ex, HttpServletResponse resp,
			RedirectAttributes redirectAttributes){
		redirectAttributes.addFlashAttribute("message", "Arquivo ultrapassa o limite de 2MB!");
		return new ModelAndView("redirect:/transacoes");

	} 
}
