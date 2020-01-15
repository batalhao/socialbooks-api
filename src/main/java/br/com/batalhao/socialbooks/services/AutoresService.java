package br.com.batalhao.socialbooks.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.batalhao.socialbooks.domain.Autor;
import br.com.batalhao.socialbooks.repository.AutoresRepository;

@Service
public class AutoresService {

	@Autowired
	private AutoresRepository autoresRepository;

	private URI buildUri(Autor autor) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autor.getId()).toUri();
	}

	public ResponseEntity<List<Autor>> list() {
		return ResponseEntity.ok(autoresRepository.findAll());
	}

	public ResponseEntity<Autor> find(Long id) {
		return autoresRepository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	public Optional<Autor> findById(Long id) {
		return autoresRepository.findById(id);
	}

	public ResponseEntity<Autor> save(Autor autor) {
		Optional<Autor> optionalAutor = autoresRepository.findByNomeAndNascimento(autor.getNome(),
				autor.getNascimento());

		if (optionalAutor.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(optionalAutor.get());
		} else {
			autor.setId(null);
			autor = autoresRepository.save(autor);
			return ResponseEntity.created(buildUri(autor)).body(autor);
		}
	}

	public ResponseEntity<Autor> update(Long id, Autor autor) {
		return autoresRepository.findById(id).map(record -> {
			autor.setId(id);
			Autor autorUpdated = autoresRepository.save(autor);
			return ResponseEntity.ok().body(autorUpdated);
		}).orElse(ResponseEntity.notFound().build());
	}

	public ResponseEntity<Object> delete(Long id) {
		return autoresRepository.findById(id).map(record -> {
			autoresRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}).orElse(ResponseEntity.notFound().build());
	}

}
