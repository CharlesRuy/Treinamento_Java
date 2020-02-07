package br.com.cast.treinamento.exceptions;

public class NotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6656758640542295773L;

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException() {
		super();
	}

}
