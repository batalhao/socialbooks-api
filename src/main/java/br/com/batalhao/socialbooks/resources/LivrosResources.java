package br.com.batalhao.socialbooks.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.batalhao.socialbooks.domain.Livro;
import br.com.batalhao.socialbooks.services.LivrosService;
import br.com.batalhao.socialbooks.utils.Utils;

@RestController
@RequestMapping(value = "/livros")
public class LivrosResources {

	@Autowired
	private LivrosService livrosService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Livro>> list(HttpServletResponse response) {
		Utils.setResponse(response);
		return livrosService.list();
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Livro> find(@PathVariable(name = "id") Long id, HttpServletResponse response) {
		Utils.setResponse(response);
		return livrosService.find(id);
	}

	@PostMapping(produces = "application/json")
	public ResponseEntity<Livro> save(@RequestBody Livro livro, HttpServletResponse response) {
		Utils.setResponse(response);
		return livrosService.save(livro);
	}

	@PutMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Livro> update(@PathVariable(name = "id") Long id, @RequestBody Livro livro,
			HttpServletResponse response) {
		Utils.setResponse(response);
		return livrosService.update(id, livro);
	}

	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> delete(@PathVariable(name = "id") Long id, HttpServletResponse response) {
		Utils.setResponse(response);
		return livrosService.delete(id);
	}

}
