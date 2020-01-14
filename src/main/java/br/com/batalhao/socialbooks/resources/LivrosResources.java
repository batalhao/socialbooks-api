package br.com.batalhao.socialbooks.resources;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.batalhao.socialbooks.domain.Livro;
import br.com.batalhao.socialbooks.repository.LivrosRepository;

@RestController
@RequestMapping(value = "/livros")
public class LivrosResources {

	@Autowired
	private LivrosRepository livrosRepository;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Livro>> list() {
		return ResponseEntity.ok(livrosRepository.findAll());
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Livro> find(@PathVariable(name = "id") Long id) {
		return livrosRepository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping(produces = "application/json")
	public ResponseEntity<Livro> save(@RequestBody Livro livro) {
		livro = livrosRepository.save(livro);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();

		return ResponseEntity.created(uri).body(livro);
	}

	@PutMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Livro> update(@PathVariable(name = "id") Long id, @RequestBody Livro livro) {
		return livrosRepository.findById(id).map(record -> {
			record.setId(id);
			Livro updated = livrosRepository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
		return livrosRepository.findById(id).map(record -> {
			livrosRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}).orElse(ResponseEntity.notFound().build());
	}

}
