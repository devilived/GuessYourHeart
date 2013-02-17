package com.devillived.guessyourheart.arithmancy;

public class CodeException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private int code;

	public CodeException(int code) {
		super("code:" + code);
		this.code = code;
	}

	public CodeException(int code, String msg) {
		super(msg);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
