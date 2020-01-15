package br.com.batalhao.socialbooks.resources;

import java.util.List;

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

import br.com.batalhao.socialbooks.domain.Autor;
import br.com.batalhao.socialbooks.services.AutoresService;

@RestController
@RequestMapping(value = "/autores")
public class AutoresResources {

	@Autowired
	private AutoresService autoresService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Autor>> list() {
		return autoresService.list();
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Autor> find(@PathVariable(name = "id") Long id) {
		return autoresService.find(id);
	}

	@PostMapping(produces = "application/json")
	public ResponseEntity<Autor> save(@RequestBody Autor autor) {
		return autoresService.save(autor);
	}

	@PutMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Autor> update(@PathVariable(name = "id") Long id, @RequestBody Autor autor) {
		return autoresService.update(id, autor);
	}

	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> delete(@PathVariable(name = "id") Long id) {
		return autoresService.delete(id);
	}

}
