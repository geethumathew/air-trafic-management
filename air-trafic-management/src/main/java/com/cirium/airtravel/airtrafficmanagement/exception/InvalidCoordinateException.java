package com.cirium.airtravel.airtrafficmanagement.exception;

public class InvalidCoordinateException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidCoordinateException(String message) {
		super(message);
	}
}