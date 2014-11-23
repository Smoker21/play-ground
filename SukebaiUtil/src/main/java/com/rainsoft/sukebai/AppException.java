package com.rainsoft.sukebai;

public class AppException extends RuntimeException {
	public final static Integer NO_FILE_PATH = 1;
	public final static Integer URL_ERROR = 2;
	private final Integer errorCode;

	public AppException(final Integer errorCode, final String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public AppException(final Integer errorCode, final String message, final Throwable th) {
		super(message, th);
		this.errorCode = errorCode;
	}
}
