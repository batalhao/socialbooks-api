package br.com.batalhao.socialbooks.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.batalhao.socialbooks.domain.Livro;
import br.com.batalhao.socialbooks.repository.LivrosRepository;

@Service
public class LivrosService {

	@Autowired
	private LivrosRepository livrosRepository;

	private URI buildUri(Livro livro) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();
	}

	public ResponseEntity<List<Livro>> list() {
		return ResponseEntity.ok(livrosRepository.findAll());
	}

	public ResponseEntity<Livro> find(Long id) {
		return livrosRepository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	public Optional<Livro> findById(Long id) {
		return livrosRepository.findById(id);
	}

	public ResponseEntity<Livro> save(Livro livro) {
		livro.setId(null);
		livro = livrosRepository.save(livro);
		return ResponseEntity.created(buildUri(livro)).body(livro);
	}

	public ResponseEntity<Livro> update(Long id, Livro livro) {
		return livrosRepository.findById(id).map(record -> {
			livro.setId(id);
			Livro livroUpdated = livrosRepository.save(livro);
			return ResponseEntity.ok().body(livroUpdated);
		}).orElse(ResponseEntity.notFound().build());
	}

	public ResponseEntity<Object> delete(Long id) {
		return livrosRepository.findById(id).map(record -> {
			livrosRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}).orElse(ResponseEntity.notFound().build());
	}

}
