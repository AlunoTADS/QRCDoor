package br.ufpr.qrcdoor.exception;

public class AcessoUnauthorizedException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public AcessoUnauthorizedException() {
	}
	
	public AcessoUnauthorizedException(String message) {
		super(message);
	}

}
