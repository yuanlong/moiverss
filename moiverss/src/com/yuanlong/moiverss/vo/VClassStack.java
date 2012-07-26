package com.yuanlong.moiverss.vo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import com.yuanlong.moiverss.channel.constant.DyZyConstant;

public class VClassStack {
	private final static List<String> VSTACK=new ArrayList<String>();
	static{
		VSTACK.addAll(DyZyConstant.CLASS_LIST);
	}
	public synchronized static void push(String cn){
		VSTACK.remove(cn);
		VSTACK.add(cn);
	}
	
	public static List<String>  peek(int len){
		List <String> list=new ArrayList<String>();
		int size=VSTACK.size();
		if(size<len){
			return VSTACK;
		}else{
		for(int i=1;i<=len;i++){
			list.add(VSTACK.get(size-i));
		}
		}
		return list;
		
	}
	public static void main(String[] args) {
		push("动作片");
		List<String> top4=peek(4);
		System.out.println(top4.get(0));
	}
}
