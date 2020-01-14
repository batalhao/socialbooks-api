package br.com.batalhao.socialbooks.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.batalhao.socialbooks.domain.Livro;
import br.com.batalhao.socialbooks.repository.LivrosRepository;

@RestController
public class LivrosResources {

	@Autowired
	private LivrosRepository livrosRepository;

	@GetMapping(value = "livros")
	public List<Livro> listar() {
		return livrosRepository.findAll();
	}

}
