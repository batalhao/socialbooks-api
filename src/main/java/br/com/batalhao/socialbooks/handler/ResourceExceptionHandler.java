package br.com.batalhao.socialbooks.handler;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.batalhao.socialbooks.domain.util.Erro;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<Erro> handlerNumberFormatException(HttpServletRequest servletRequest) {
		return badRequest("https://socialbooks.batalhao.com.br/400/NumberFormatException");
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Erro> handlerHttpMessageNotReadableException(HttpServletRequest servletRequest) {
		return badRequest("https://socialbooks.batalhao.com.br/400/HttpMessageNotReadableException");
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Erro> handlerHttpRequestMethodNotSupportedException(HttpServletRequest servletRequest) {
		return badRequest("https://socialbooks.batalhao.com.br/400/HttpRequestMethodNotSupportedException");
	}

	public ResponseEntity<Erro> badRequest(String message) {
		Erro erro = new Erro();

		erro.setStatus(HttpStatus.BAD_REQUEST.toString());
		erro.setDate(LocalDate.now());
		erro.setTime(LocalTime.now());
		erro.setMessage(message);

		return ResponseEntity.badRequest().body(erro);
	}

}
