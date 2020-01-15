package br.com.batalhao.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.batalhao.socialbooks.domain.Autor;

public interface AutoresRepository extends JpaRepository<Autor, Long> {

}
