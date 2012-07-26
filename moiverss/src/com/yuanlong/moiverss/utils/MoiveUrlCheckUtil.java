package com.yuanlong.moiverss.utils;

public class MoiveUrlCheckUtil {
	public static String checkUrl(String murl){
		if (murl.endsWith("|")){
			return murl;
		}else{
			return murl+"|";
		}
	}
	public static void main(String[] args) {
		System.out.println(checkUrl("qvod://1056371565|FD2C7E7187189E412718E1D07D3A79087286284E|想听到你说爱_Best.Supporting.Actor_2010_BD.rmvb"));
	}
}
