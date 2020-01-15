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

import br.com.batalhao.socialbooks.domain.Comentario;
import br.com.batalhao.socialbooks.services.ComentariosService;

@RestController
@RequestMapping
public class ComentariosResources {

	@Autowired
	private ComentariosService comentariosService;

	@GetMapping(path = "/livros/comentarios", produces = "application/json")
	public ResponseEntity<List<Comentario>> list() {
		return comentariosService.list();
	}

	@GetMapping(value = "/livros/comentarios/{id}", produces = "application/json")
	public ResponseEntity<Comentario> find(@PathVariable(name = "id") Long id) {
		return comentariosService.find(id);
	}

	@PostMapping(value = "/livros/{livroId}/comentarios", produces = "application/json")
	public ResponseEntity<Comentario> save(@PathVariable(name = "livroId") Long livroId,
			@RequestBody Comentario comentario) {
		return comentariosService.save(livroId, comentario);
	}

	@PutMapping(value = "/livros/comentarios/{id}", produces = "application/json")
	public ResponseEntity<Comentario> update(@PathVariable(name = "id") Long id, @RequestBody Comentario comentario) {
		return comentariosService.update(id, comentario);
	}

	@DeleteMapping(value = "/livros/comentarios/{id}", produces = "application/json")
	public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
		return comentariosService.delete(id);
	}

}
