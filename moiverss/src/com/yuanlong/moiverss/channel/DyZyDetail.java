package com.yuanlong.moiverss.channel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.yuanlong.moiverss.channel.base.DetailBase;
import com.yuanlong.moiverss.utils.DateUtil;
import com.yuanlong.moiverss.vo.MoiveDetail;
import com.yuanlong.moiverss.vo.VClassStack;

public class DyZyDetail extends DetailBase{
	public DyZyDetail(String url){
		super(url);
	}
	@Override
	public void doParse() throws IOException {
		Document doc=Jsoup.connect(url).get();
		Element pic_element=doc.getElementsByClass("moviePic").first();
		if (pic_element==null){
			return;
		}
		String pic=pic_element.child(0).attr("src");
	
		Element qvodData=doc.getElementById("qvod");
		if (qvodData==null){
			return;
		}
		Elements elemnts=qvodData.getElementsByTag("li");
		if(elemnts.size()<1){return ;}
		List <String> vdata=new ArrayList<String>();
		for(int i=0;i<elemnts.size();i++){
			vdata.add(elemnts.get(i).text());
		}
		Element movieDetail=doc.getElementsByClass("movieDetail").first();
		if (movieDetail==null){
			return;
		}
		Elements elms=movieDetail.children();
		if(elms.size()!=9)return;
		String name=elms.get(0).ownText();
		String vclass=elms.get(1).ownText();
		String area=elms.get(2).ownText();
		String author=elms.get(5).ownText();
		String direct=elms.get(6).ownText();
		String year=elms.get(7).ownText();
		String utime_Str=elms.get(8).ownText();
		Date utime=DateUtil.parse(utime_Str, DateUtil.DATEFORMAT_YYYY_MM_DD_HH_MM_SS);
		VClassStack.push(vclass);
		mdetail=new MoiveDetail(name, pic, utime, year, vdata, area, vclass, direct, author);
	}
	
	public static void main(String[] args) {
		DetailBase channel=new DyZyDetail("http://www.dyzy.cc/detail.asp?ID=6699");
		try {
			channel.doParse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	System.out.println(channel.getMdetail());
	}
	
	
}
