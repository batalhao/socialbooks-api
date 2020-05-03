package br.com.batalhao.socialbooks.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.batalhao.socialbooks.domain.Autor;
import br.com.batalhao.socialbooks.domain.Livro;
import br.com.batalhao.socialbooks.repository.AutoresRepository;
import br.com.batalhao.socialbooks.repository.LivrosRepository;

@Service
public class LivrosService {

	@Autowired
	private LivrosRepository livrosRepository;

	@Autowired
	private AutoresRepository autoresRepository;

	private URI buildUri(Livro livro) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();
	}

	public ResponseEntity<List<Livro>> list(CacheControl cacheControl) {
		return ResponseEntity.ok().cacheControl(cacheControl).body(livrosRepository.findAll());
	}

	public ResponseEntity<Livro> find(Long id, CacheControl cacheControl) {
		return livrosRepository.findById(id).map(record -> ResponseEntity.ok().cacheControl(cacheControl).body(record))
				.orElse(ResponseEntity.notFound().cacheControl(cacheControl).build());
	}

	public Optional<Livro> findById(Long id) {
		return livrosRepository.findById(id);
	}

	public ResponseEntity<Livro> save(Livro livro) {
		if (livro.getAutor() != null) {
			Autor autor = autoresRepository.findById(livro.getAutor().getId()).orElse(null);
			if (autor == null)
				return ResponseEntity.badRequest().build();
		}

		Optional<Livro> optionalLivro = livrosRepository.findByNomeAndAutor(livro.getNome(), livro.getAutor());

		if (optionalLivro.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(optionalLivro.get());
		} else {
			livro.setId(null);
			livro = livrosRepository.save(livro);
			return ResponseEntity.created(buildUri(livro)).body(livro);
		}
	}

	public ResponseEntity<Livro> update(Long id, Livro livro) {
		if (livro.getAutor() != null) {
			Autor autor = autoresRepository.findById(livro.getAutor().getId()).orElse(null);
			if (autor == null)
				return ResponseEntity.badRequest().build();
		}

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

	public Optional<List<Livro>> findByNome(String nomeLivro) {
		return livrosRepository.findByNome(nomeLivro);
	}

}
