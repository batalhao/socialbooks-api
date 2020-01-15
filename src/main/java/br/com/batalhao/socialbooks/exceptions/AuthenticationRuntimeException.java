package br.com.batalhao.socialbooks.exceptions;

public class AuthenticationRuntimeException extends Exception {

	private static final long serialVersionUID = -7156695023500310818L;

	public AuthenticationRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

}
