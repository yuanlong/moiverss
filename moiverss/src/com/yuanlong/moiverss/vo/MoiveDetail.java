package com.yuanlong.moiverss.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;

import com.yuanlong.moiverss.utils.DateUtil;
import com.yuanlong.moiverss.utils.MD5Util;

@PersistenceCapable
public class MoiveDetail implements Serializable{
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	@Unique
	private String name;// 影片名称
	@Persistent
	private String pic;// 图片
	@Persistent
	private Date utime;// 创建时间
	@Persistent
	private String year;// 年份
	@Persistent
	private List<String> vdata = new ArrayList<String>();// 视频数据
	@Persistent
	private String area;// 地区
	@Persistent
	private String vclass;// 类型
	@Persistent
	private String direct;// 导演
	@Persistent
	private String author;// 主演

	public MoiveDetail() {
	}

	public MoiveDetail(String name, String pic, Date utime, String year,
			List<String> vdata, String area, String vclass, String direct,
			String author) {
		super();
		this.name = name;
		this.pic = pic;
		this.utime = utime;
		this.year = year;
		this.vdata = vdata;
		this.area = area;
		this.vclass = vclass;
		this.direct = direct;
		this.author = author;
	}

	public MoiveDetail(String name, String pic, String year,
			List<String> vdata, String area, String vclass, String direct,
			String author) {
		super();
		this.name = name;
		this.pic = pic;
		this.utime = new Date();
		this.year = year;
		this.vdata = vdata;
		this.area = area;
		this.vclass = vclass;
		this.direct = direct;
		this.author = author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Date getUtime() {
		return utime;
	}

	public void setUtime(Date utime) {
		this.utime = utime;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<String> getVdata() {
		return vdata;
	}

	public void setVdata(List<String> vdata) {
		this.vdata = vdata;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Long getId() {
		return id;
	}

	public String getDirect() {
		return direct;
	}

	public void setDirect(String direct) {
		this.direct = direct;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getVclass() {
		return vclass;
	}

	public void setVclass(String vclass) {
		this.vclass = vclass;
	}

	@Override
	public String toString() {
		return name
				+ "#"
				+ pic
				+ "#"
				+ DateUtil.format(utime,
						DateUtil.DATEFORMAT_YYYY_MM_DD_HH_MM_SS) + "#" + year
				+ "#" + area + "#" + vclass + "#" + direct + "#" + author + "#"
				+ vdata.toString();
	}

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("123");
		list.add("456");
		System.out.println(list.toString());
	}
}
