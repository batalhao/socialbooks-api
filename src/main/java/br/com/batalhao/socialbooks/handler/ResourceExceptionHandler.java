package br.com.batalhao.socialbooks.handler;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.batalhao.socialbooks.domain.Erro;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<Erro> handlerException(HttpServletRequest servletRequest) {

		Erro erro = new Erro();

		erro.setStatus(HttpStatus.BAD_REQUEST.toString());
		erro.setDate(LocalDate.now());
		erro.setTime(LocalTime.now());
		erro.setMessage("https://socialbooks.batalhao.com.br/400");

		return ResponseEntity.badRequest().body(erro);
	}

}
