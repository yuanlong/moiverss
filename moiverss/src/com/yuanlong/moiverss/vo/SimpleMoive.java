package com.yuanlong.moiverss.vo;

import java.util.Date;

public class SimpleMoive {
	private String name;
	private String vclass;
	private String area;
	private Date utime;
	private String url;
	
	public SimpleMoive() {
	}
	
	public SimpleMoive(String name, String vclass, String area, Date utime,
			String url) {
		super();
		this.name = name;
		this.vclass = vclass;
		this.area = area;
		this.utime = utime;
		this.url = url;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVclass() {
		return vclass;
	}
	public void setVclass(String vclass) {
		this.vclass = vclass;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	public Date getUtime() {
		return utime;
	}

	public void setUtime(Date utime) {
		this.utime = utime;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
