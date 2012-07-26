package com.yuanlong.moiverss.utils;

import java.io.IOException;

import org.jsoup.Jsoup;

public class Test {
	public static void main(String[] args) {
		for(int i=0;i<3285;i++){
			try {
				Jsoup.connect("http://www.dyrss.co.cc/tasks/import").get();
				System.out.println(i);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
