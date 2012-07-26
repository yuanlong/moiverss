package com.yuanlong.moiverss.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yuanlong.moiverss.vo.MoiveDetail;

public class Global {
	public final static String DOMAIN=PropertyManager.getString("domain"); 
	
	public static List<MoiveDetail> mdlist=new ArrayList<MoiveDetail>();
	public static int index=0;
	
	public static MoiveDetail getNextMoive(){
		if (index==mdlist.size()){
			return null;
		}
		return mdlist.get(index++);
	}
	
	public static int status=1;
	
	public  static int collectCount=0;
	public static int willCollectCount=0;
	public static Date lastCollectTime=new Date();
	public static Date newCollectTime=new Date();
	public static List<String> collectList=new ArrayList<String>();
	
	public final static String sina=PropertyManager.getString("sina");
	public final static String email=PropertyManager.getString("email");
	public final static String qq=PropertyManager.getString("qq");
	
	public static int tempCount=0;
	public static Date lastTempTime=DateUtil.get8Date();
}
