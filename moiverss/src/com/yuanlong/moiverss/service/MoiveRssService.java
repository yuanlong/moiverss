package com.yuanlong.moiverss.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rsslibj.elements.Channel;
import com.yuanlong.moiverss.utils.DateUtil;
import com.yuanlong.moiverss.utils.Global;
import com.yuanlong.moiverss.utils.VelocityUtils;
import com.yuanlong.moiverss.vo.MoiveDetail;

public class MoiveRssService {
	private MoiveStoreService moiveStoreService;
	
	public MoiveStoreService getMoiveStoreService() {
		return moiveStoreService;
	}

	public void setMoiveStoreService(MoiveStoreService moiveStoreService) {
		this.moiveStoreService = moiveStoreService;
	}

	public String generateRssXml(String vm) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		Channel channel=new Channel();
		channel.setTitle("免费高清快播电影订阅中心");
		channel.setDescription("本网站致力于提供最新高清QVOD电影播放地址。所有资源均来自网络。如有侵犯您的权益请联系我们：新浪微博： @"+Global.sina+" 邮箱："+Global.email+" QQ："+Global.qq);
		channel.setCopyright("Copyright © 2012 MoiveRSS");
		channel.setAbout("本站使用Google App Engine开发。");
		channel.setLink(Global.DOMAIN);
		channel.setImage(Global.DOMAIN, 
	                "免费高清快播电影订阅中心", 
	                Global.DOMAIN+"images/logo.png");
		List<MoiveDetail> list=moiveStoreService.queryMoive(40);
		for(MoiveDetail md:list){
			channel.addItem(Global.DOMAIN+"detail?id="+md.getId(), parseRssTmpl(vm,md), md.getName());
		}
		return channel.getFeed("2.0");
	}
	
	private String parseRssTmpl(String vm,MoiveDetail md){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("v_pic", md.getPic());
		map.put("v_name", md.getName());
		map.put("v_class", md.getVclass());
		map.put("v_area", md.getArea());
		map.put("v_author", md.getAuthor());
		map.put("v_direct", md.getDirect());
		map.put("v_year", md.getYear());
		map.put("v_ctime", DateUtil.format(md.getUtime(), DateUtil.DATEFORMAT_YYYY_MM_DD_HH_MM_SS));
		map.put("v_datalist", md.getVdata());
		String content=VelocityUtils.getInstance().mergeToString(vm,map);
		return content;
	}
}
