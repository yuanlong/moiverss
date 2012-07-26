package com.yuanlong.moiverss.utils;

import java.util.regex.*;

public class RegexUtil {

	/**
	 * 查找
	 * @param source_code 原字符串
	 * @param reg_ex 正则表达式
	 * @return 查找结果
	 */
	public static String searchExp(String source_code, String reg_ex) {
		if(source_code==null)source_code="";
		String currentMatch = "";
		Pattern p = Pattern.compile(reg_ex);
		Matcher propsMatcher = p.matcher(source_code);
		if (propsMatcher.find()) {
			int startIndex = propsMatcher.start();
			int endIndex = propsMatcher.end();
			currentMatch = source_code.substring(startIndex, endIndex);
		}
		return String.valueOf(currentMatch);
	}

	/**
	 * 替换
	 * @param source_code 原字符串
	 * @param exp_str 正则表达式
	 * @param rep_str	替换内容
	 * @return 替换后的字符串
	 */
	public static String replaceExp(String source_code, String exp_str,
			String rep_str) {
		return source_code.replaceAll(exp_str, rep_str);
	}

	
	public static void main(String[] ags) {
		System.out.println(searchExp("454545<title>abcdefg</title>45455","<title>(.*?)</title>"));
		//System.out.println(replaceExp("http://player.youku.com/player.php/sid/XMTMyOTc3NTY4=/v.swf","player.youku.com/player.php/sid/", "v.youku.com/v_show/id_"));
	}
}
