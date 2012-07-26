package com.yuanlong.moiverss.channel.base;

import java.io.IOException;

public abstract class Base {
	protected String url = "";
	protected String charset = "UTF-8";
	/**
	 * parse
	 * @return
	 */
	public abstract  void doParse() throws IOException;

	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getCharset() {
		return charset;
	}


	public void setCharset(String charset) {
		this.charset = charset;
	}


}
