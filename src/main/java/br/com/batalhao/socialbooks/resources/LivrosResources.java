package br.com.batalhao.socialbooks.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LivrosResources {

	@GetMapping(value = "livros")
	public String listar() {
		return "Rest aplicado, Git passo a passo";
	}

}
