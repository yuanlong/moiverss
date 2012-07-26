package com.yuanlong.moiverss.exception;

@SuppressWarnings("serial")
public class NetException extends MoiveRssBaseException {
	public NetException() {
	}

	public NetException(String message, Throwable cause) {
		super(message, cause);
	}

	public NetException(String message) {
		super(message);
	}

	public NetException(Throwable cause) {
		super(cause);
	}
}
