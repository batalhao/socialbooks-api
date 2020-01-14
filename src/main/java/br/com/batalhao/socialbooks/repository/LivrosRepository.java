package br.com.batalhao.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.batalhao.socialbooks.domain.Livro;

public interface LivrosRepository extends JpaRepository<Livro, Long> {

}
