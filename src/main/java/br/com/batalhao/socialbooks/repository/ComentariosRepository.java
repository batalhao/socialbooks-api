package br.com.batalhao.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.batalhao.socialbooks.domain.Comentario;

public interface ComentariosRepository extends JpaRepository<Comentario, Long> {

}
