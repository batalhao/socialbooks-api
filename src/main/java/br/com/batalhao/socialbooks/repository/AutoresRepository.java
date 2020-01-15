package br.com.batalhao.socialbooks.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.batalhao.socialbooks.domain.Autor;

public interface AutoresRepository extends JpaRepository<Autor, Long> {

	public Optional<Autor> findByNomeAndNascimento(String nome, LocalDate nascimento);

}
