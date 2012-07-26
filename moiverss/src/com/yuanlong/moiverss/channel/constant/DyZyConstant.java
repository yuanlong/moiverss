package com.yuanlong.moiverss.channel.constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DyZyConstant {
	private static final String[] classArr={"动作片","喜剧片","恐怖片","剧情片","纪录片","爱情片","科幻片","战争片","动漫频道"};
	public static final List<String> CLASS_LIST=Arrays.asList(classArr);
	
	public static final String LIST_REGEX_STR="<tbody id=\"data_list\"> [\\W*\\w*]*?</tbody></table>";
	
	public static final String DYZY_DOMAIN="http://www.dyzy.cc/";
	
	public static final int PAGE_COUNT=30;
	private static final String DYZY_LIST_URL="http://www.dyzy.cc/index.asp?KeyWord=BD&page=$page";
	
	public static String getVedioDetailUrl(String url){
		return DYZY_DOMAIN+url;
	}
	
	public static String getDyzyCollectUrl(int page){
		return DYZY_LIST_URL.replaceAll("\\$page", ""+page);
	}
	public static String getDyzyCollectUrl(String url,int page){
		return url+"&page="+page;
	}
}
