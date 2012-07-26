package com.yuanlong.moiverss.channel;

import java.io.IOException;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.yuanlong.moiverss.channel.base.ListBase;
import com.yuanlong.moiverss.channel.constant.DyZyConstant;
import com.yuanlong.moiverss.utils.DateUtil;
import com.yuanlong.moiverss.utils.Global;
import com.yuanlong.moiverss.vo.SimpleMoive;

public class DyZyList extends ListBase {
	public static Date date=DateUtil.get8Date();
	public DyZyList(String url) {
		super(url);
	}
	@Override
	public void doParse() throws IOException {
		Document doc=Jsoup.connect(url).get();
		Element data_list_element=doc.getElementById("data_list");
		Elements dataElements=data_list_element.children();
		for(int i=0;i<dataElements.size();i++){
			SimpleMoive s=parseElement(dataElements.get(i));
			if(s!=null){
				if (!DateUtil.isAfter(s.getUtime(),DyZyList.date)){
					break;
				}
				if(DyZyConstant.CLASS_LIST.contains(s.getVclass())){
					list.add(s);
					Global.collectList.add(s.getName());
				}
			}
		}
	}
	@Override
	public void doNoLimitParse() throws IOException {
		Document doc=Jsoup.connect(url).get();
		Element data_list_element=doc.getElementById("data_list");
		Elements dataElements=data_list_element.children();
		for(int i=0;i<dataElements.size();i++){
			SimpleMoive s=parseElement(dataElements.get(i));
			if(s!=null){
//				if (!DateUtil.isAfter(s.getUtime(),DyZyList.date)){
//					break;
//				}
				if(DyZyConstant.CLASS_LIST.contains(s.getVclass())){
					list.add(s);
					Global.collectList.add(s.getName());
				}
			}
		}
	}
	/**
	 * parse
	 * @param element
	 * @return
	 */
	private SimpleMoive parseElement(Element element) {
		Elements childs=element.children();
		if(childs.size()!=5) return null;
		String url=childs.get(0).child(0).attr("href");
		String name=childs.get(0).text();
		String vclass=childs.get(1).text();
		String area=childs.get(2).text();
		String _utime=childs.get(4).text();
		Date utime=DateUtil.parse(_utime, DateUtil.DATEFORMAT_YYYY_MM_DD_HH_MM_SS);
		return new SimpleMoive(name, vclass, area, utime, url);
	}
	
	public static void main(String[] args) {
		ListBase channel=new DyZyList("http://www.dyzy.cc/index.asp?KeyWord=BD&page=1");
		try {
			channel.doParse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
