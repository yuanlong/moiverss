package com.yuanlong.moiverss.channel.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.yuanlong.moiverss.vo.SimpleMoive;

public abstract class ListBase extends Base{
	protected List<SimpleMoive> list=new ArrayList<SimpleMoive>();
	
	public ListBase(String url) {
		super();
		this.url = url;
	}


	public ListBase(String url, String charset) {
		super();
		this.url = url;
		this.charset = charset;
	}



	public List<SimpleMoive> getList() {
		return list;
	}


	public void setList(List<SimpleMoive> list) {
		this.list = list;
	}
	/**
	 * parse
	 * @return
	 */
	public abstract  void doNoLimitParse() throws IOException;


}
