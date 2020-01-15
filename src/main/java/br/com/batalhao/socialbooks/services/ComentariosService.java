package br.com.batalhao.socialbooks.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.batalhao.socialbooks.domain.Comentario;
import br.com.batalhao.socialbooks.domain.Livro;
import br.com.batalhao.socialbooks.repository.ComentariosRepository;

@Service
public class ComentariosService {

	@Autowired
	private ComentariosRepository comentariosRepository;

	@Autowired
	private LivrosService livrosService;

	private URI buildUri(Comentario comentario) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(comentario.getId())
				.toUri();
	}

	public ResponseEntity<List<Comentario>> list() {
		return ResponseEntity.ok(comentariosRepository.findAll());
	}

	public ResponseEntity<Comentario> find(Long id) {
		return comentariosRepository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	public ResponseEntity<Comentario> save(Long livroID, Comentario comentario) {
		Optional<Livro> livro = livrosService.findById(livroID);

		if (livro.isPresent()) {
			comentario.setId(null);
			comentario.setLivro(livro.get());
			comentario = comentariosRepository.save(comentario);
			return ResponseEntity.created(buildUri(comentario)).body(comentario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	public ResponseEntity<Comentario> update(Long id, Comentario comentario) {
		return comentariosRepository.findById(id).map(record -> {
			comentario.setId(id);
			comentario.setLivro(record.getLivro());
			Comentario comentarioUpdated = comentariosRepository.save(comentario);
			return ResponseEntity.ok().body(comentarioUpdated);
		}).orElse(ResponseEntity.notFound().build());
	}

	public ResponseEntity<?> delete(Long id) {
		return comentariosRepository.findById(id).map(record -> {
			comentariosRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}).orElse(ResponseEntity.notFound().build());
	}

}
