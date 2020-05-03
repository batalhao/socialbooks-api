package br.com.batalhao.socialbooks.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.batalhao.socialbooks.domain.Autor;
import br.com.batalhao.socialbooks.domain.Livro;

public interface LivrosRepository extends JpaRepository<Livro, Long> {

	public Optional<Livro> findByNomeAndAutor(String nome, Autor autor);

	public Optional<List<Livro>> findByNome(String nomeLivro);

}
