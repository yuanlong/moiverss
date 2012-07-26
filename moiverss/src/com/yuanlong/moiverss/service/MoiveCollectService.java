package com.yuanlong.moiverss.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.yuanlong.moiverss.channel.DyZyDetail;
import com.yuanlong.moiverss.channel.DyZyList;
import com.yuanlong.moiverss.channel.base.DetailBase;
import com.yuanlong.moiverss.channel.base.ListBase;
import com.yuanlong.moiverss.channel.constant.DyZyConstant;
import com.yuanlong.moiverss.utils.DateUtil;
import com.yuanlong.moiverss.utils.Global;
import com.yuanlong.moiverss.vo.MoiveDetail;
import com.yuanlong.moiverss.vo.SimpleMoive;

public class MoiveCollectService {
	  private static final Logger logger = Logger.getLogger(MoiveCollectService.class.getCanonicalName());

	private MoiveStoreService moiveStoreService;
	
	public MoiveStoreService getMoiveStoreService() {
		return moiveStoreService;
	}

	public void setMoiveStoreService(MoiveStoreService moiveStoreService) {
		this.moiveStoreService = moiveStoreService;
	}

	public int doCollectDyzy(){
//		long t=System.currentTimeMillis();
		int page=1;
		int count=collectByStartPage(page);
		
//		collectByFPageTPage(1,112);
//		return 0;
		return count;
	}

	public void collectByFPageTPage(int fpage,int tpage){
		List<SimpleMoive> list=new ArrayList<SimpleMoive>();
		List<SimpleMoive> clist=new ArrayList<SimpleMoive>();
		for(int i=fpage;i<=tpage;i++){
			list=collectList(i);
			if(!list.isEmpty()){
				clist.addAll(list);
			}
		}
		
		List<MoiveDetail> mdList=new ArrayList<MoiveDetail>();
		int i=0;
		for(SimpleMoive sm:clist){
			MoiveDetail md=collectMoive(DyZyConstant.getVedioDetailUrl(sm.getUrl()));
			if(md!=null){
				mdList.add(md);
			}
			i++;
			if(i%100==0){
				System.out.println("finished "+i+"!");
			}
		}
		try {
			write(mdList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void collectByFPageTPage(String url,int fpage,int tpage){
//		System.out.println("开始采集："+url+" 起始页码："+fpage+" 终止页码："+tpage);
		List<SimpleMoive> list=new ArrayList<SimpleMoive>();
		List<SimpleMoive> clist=new ArrayList<SimpleMoive>();
		for(int i=fpage;i<=tpage;i++){
			try {
				list=collectList(DyZyConstant.getDyzyCollectUrl(url, i),i,false);
				if(!list.isEmpty()){
					clist.addAll(list);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Global.tempCount=0;
		int count =0;
		for(SimpleMoive sm:clist){
			if(!moiveStoreService.isExist(sm.getName())){
				MoiveDetail md=collectMoive(DyZyConstant.getVedioDetailUrl(sm.getUrl()));
				if(md!=null){
					moiveStoreService.saveMoive(md);
					count++;
				}
			}			
		}
		Global.tempCount=count;
		Global.lastTempTime=new Date();
		System.out.println("临时采集了："+count+"部");
	}
	private int collectByStartPage(int page) {
		List<SimpleMoive> list=new ArrayList<SimpleMoive>();
		List<SimpleMoive> clist=new ArrayList<SimpleMoive>();
		list=collectList(page);
		while(!list.isEmpty()){
			clist.addAll(list);
			page++;
			list=collectList(page);
		}
		Global.willCollectCount=clist.size();
		int count =0;
		for(SimpleMoive sm:clist){
			if(!moiveStoreService.isExist(sm.getName())){
				MoiveDetail md=collectMoive(DyZyConstant.getVedioDetailUrl(sm.getUrl()));
				if(md!=null){
					moiveStoreService.saveMoive(md);
					Global.collectCount++;
					count++;
				}
			}			
		} 
		if(count>0){
			Global.newCollectTime=DateUtil.get8Date();
		}
		return count;
	/*	List<MoiveDetail> mdList=new ArrayList<MoiveDetail>();
		int i=0;
		for(SimpleMoive sm:clist){
			MoiveDetail md=collectMoive(DyZyConstant.getVedioDetailUrl(sm.getUrl()));
			if(md!=null){
				mdList.add(md);
			}
			i++;
			if(i%100==0){
				System.out.println("finished "+i+"!");
			}
		}
		try {
			write(mdList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	private MoiveDetail collectMoive(String url){
		DetailBase mChannel=new DyZyDetail(url);
		try {
			mChannel.doParse();
			return mChannel.getMdetail();
		} catch (IOException e) {
			return null;
		}
	}
	
	public List<SimpleMoive> collectList(String url,int page,boolean limit){
		logger.log(Level.INFO,"PageNo:"+page);
		List<SimpleMoive> list=new ArrayList<SimpleMoive>();
		ListBase listChannel=new DyZyList(url);
		try {
			if(limit){
				listChannel.doParse();
			}else{
				listChannel.doNoLimitParse();
			}
			list=listChannel.getList();
		} catch (IOException e) {
			try {
				if(limit){
					listChannel.doParse();
				}else{
					listChannel.doNoLimitParse();
				}
				list=listChannel.getList();
			} catch (IOException e1) {
				try {
					if(limit){
						listChannel.doParse();
					}else{
						listChannel.doNoLimitParse();
					}
					list=listChannel.getList();
				} catch (IOException e2) {
					logger.log(Level.INFO,"error PageNo:"+page);
					e2.printStackTrace();
					list=new ArrayList<SimpleMoive>();
				}
			}
			
		}
		return list;
	}
	
	private List<SimpleMoive> collectList(int page){
		return collectList(DyZyConstant.getDyzyCollectUrl(page), page,true);
	}
	private void write(List<MoiveDetail> mdList) throws IOException{
		BufferedWriter out=null;
		try {
			out=new BufferedWriter(new FileWriter("m.txt"));
			for(MoiveDetail md:mdList){
				out.append(md.toString());
				out.newLine();
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out!=null){
				out.close();
			}
		}
	}
	public int importMoive(){
//		long t=System.currentTimeMillis();
		int count=0;
		try {
			List<MoiveDetail> mdlist=readMoive();
			Global.mdlist=mdlist;
			for(MoiveDetail md:mdlist){
				if(!moiveStoreService.isExist(md.getName())){
						moiveStoreService.saveMoive(md);
						count++;
				}			
			}
			logger.log(Level.INFO,"The count is :"+count+"");
		} catch (IOException e) {
			e.printStackTrace();
		}
	
//		log.log(Level.INFO,"All time:"+(System.currentTimeMillis()-t));
		return count;
	}
	
	private List<MoiveDetail> readMoive() throws IOException{
		List<MoiveDetail> list=new ArrayList<MoiveDetail>();
		List<String> mlist=new ArrayList<String> ();
		BufferedReader read=null;
		String s=null;
		try {
			File file=new File("m.txt");
			System.out.println(file.getAbsolutePath());
			read=new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
			while((s=read.readLine())!=null){
				mlist.add(s);
			}
			System.out.println("over!");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{
			if(read!=null){
				read.close();
			}
		}
		for(int i=mlist.size()-1;i>=0;i--){
			MoiveDetail md=parse(mlist.get(i));
			if(md!=null){
				list.add(md);
			}
		}
		return list;
	}
	
	private MoiveDetail parse(String s) {
		String[] sarr=s.split("#");
		if (sarr.length!=9){
		return null;
		}
		return new MoiveDetail(sarr[0],sarr[1],DateUtil.parse(sarr[2], DateUtil.DATEFORMAT_YYYY_MM_DD_HH_MM_SS),sarr[3],parseList(sarr[8]),sarr[4],sarr[5],sarr[6],sarr[7]);
	}
	private List<String> parseList(String s){
		String[] arr=s.substring(1, s.length()-2).split(",");
		return Arrays.asList(arr);
	}
	public static void main(String[] args) {
		
		
		MoiveCollectService mcs=new MoiveCollectService();
		mcs.doCollectDyzy();
//		mcs.importMoive();
		
		
	}

	public void collectByUrl(String url) {
		MoiveDetail md=collectMoive(url);
		System.out.println(md);
		if(md!=null&&!moiveStoreService.isExist(md.getName())){
			if(!DyZyConstant.CLASS_LIST.contains(md.getVclass())){
				md.setVclass(DyZyConstant.CLASS_LIST.get(0));
			}
			moiveStoreService.saveMoive(md);
		}
	}
}
