package br.com.cast.treinamento.exceptions;

public class BadRequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4613286153165519724L;

	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException() {
		super();
	}
}
