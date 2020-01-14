package br.com.batalhao.socialbooks.resources;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.batalhao.socialbooks.domain.Livro;

@RestController
public class LivrosResources {

	@GetMapping(value = "livros")
	public List<Livro> listar() {
		Livro l1 = new Livro();
		l1.setNome("Nome do primeiro livro");

		Livro l2 = new Livro();
		l2.setNome("Nome do segundo livro");
		l2.setPublicacao(LocalDate.now());

		List<Livro> lista = Arrays.asList(l1, l2);

		return lista;
	}

}
