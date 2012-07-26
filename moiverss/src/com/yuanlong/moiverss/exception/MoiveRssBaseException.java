package com.yuanlong.moiverss.exception;

@SuppressWarnings("serial")
public abstract class MoiveRssBaseException extends RuntimeException {
	public MoiveRssBaseException() {
	}

	public MoiveRssBaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public MoiveRssBaseException(String message) {
		super(message);
	}

	public MoiveRssBaseException(Throwable cause) {
		super(cause);
	}
}