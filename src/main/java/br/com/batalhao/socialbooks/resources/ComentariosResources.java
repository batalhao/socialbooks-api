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

import br.com.batalhao.socialbooks.domain.Comentario;
import br.com.batalhao.socialbooks.services.ComentariosService;
import br.com.batalhao.socialbooks.utils.Utils;

@RestController
@RequestMapping(value = "/livros")
public class ComentariosResources {

	@Autowired
	private ComentariosService comentariosService;

	@GetMapping(path = "/comentarios", produces = "application/json")
	public ResponseEntity<List<Comentario>> list(HttpServletResponse response) {
		Utils.setResponse(response);
		return comentariosService.list();
	}

	@GetMapping(value = "/comentarios/{id}", produces = "application/json")
	public ResponseEntity<Comentario> find(@PathVariable(name = "id") Long id, HttpServletResponse response) {
		Utils.setResponse(response);
		return comentariosService.find(id);
	}

	@GetMapping(value = "/{livroId}/comentarios", produces = "application/json")
	public ResponseEntity<List<Comentario>> findByLivro(@PathVariable(name = "livroId") Long livroId,
			HttpServletResponse response) {
		Utils.setResponse(response);
		return comentariosService.findByLivro(livroId);
	}

	@PostMapping(value = "/{livroId}/comentarios", produces = "application/json")
	public ResponseEntity<Comentario> save(@PathVariable(name = "livroId") Long livroId,
			@RequestBody Comentario comentario, HttpServletResponse response) {
		Utils.setResponse(response);
		return comentariosService.save(livroId, comentario);
	}

	@PutMapping(value = "/comentarios/{id}", produces = "application/json")
	public ResponseEntity<Comentario> update(@PathVariable(name = "id") Long id, @RequestBody Comentario comentario,
			HttpServletResponse response) {
		Utils.setResponse(response);
		return comentariosService.update(id, comentario);
	}

	@DeleteMapping(value = "/comentarios/{id}", produces = "application/json")
	public ResponseEntity<Object> delete(@PathVariable(name = "id") Long id, HttpServletResponse response) {
		Utils.setResponse(response);
		return comentariosService.delete(id);
	}

}
