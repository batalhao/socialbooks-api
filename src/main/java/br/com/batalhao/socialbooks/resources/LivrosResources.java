package br.com.batalhao.socialbooks.resources;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
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

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Livro>> list(HttpServletResponse response) {
		Utils.setResponse(response);
		CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);
		return livrosService.list(cacheControl);
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Livro> find(@PathVariable(name = "id") Long id, HttpServletResponse response) {
		Utils.setResponse(response);

		CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);
		return livrosService.find(id, cacheControl);
	}

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Livro> save(@RequestBody @Valid Livro livro, HttpServletResponse response) {
		Utils.setResponse(response);
		return livrosService.save(livro);
	}

	@PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Livro> update(@PathVariable(name = "id") Long id, @RequestBody @Valid Livro livro,
			HttpServletResponse response) {
		Utils.setResponse(response);
		return livrosService.update(id, livro);
	}

	@DeleteMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> delete(@PathVariable(name = "id") Long id, HttpServletResponse response) {
		Utils.setResponse(response);
		return livrosService.delete(id);
	}

}
