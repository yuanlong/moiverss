package com.yuanlong.moiverss.v2.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuanlong.moiverss.base.AppContext;
import com.yuanlong.moiverss.channel.constant.DyZyConstant;
import com.yuanlong.moiverss.service.MoiveStoreService;
import com.yuanlong.moiverss.utils.Global;
import com.yuanlong.moiverss.utils.PageInfo;
import com.yuanlong.moiverss.vo.MoiveDetail;
import com.yuanlong.moiverss.vo.VClassStack;

public class MoiveIndexServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		MoiveStoreService moiveStoreService = (MoiveStoreService) AppContext
				.getBean("moiveStoreService");
		List<String> top4=VClassStack.peek(4);
		Map<String,List<MoiveDetail>> map=new HashMap<String,List<MoiveDetail>>();
		PageInfo tpg=new PageInfo(1, 5);
		for(String s:top4){
			map.put(s, moiveStoreService.queryMoive(tpg, s));
		}
		PageInfo spg;
		Map<String,List<MoiveDetail>> smap=new HashMap<String,List<MoiveDetail>>();
		for(String s:DyZyConstant.CLASS_LIST){
			spg=new PageInfo(1,16);
			if(top4.contains(s)){
				spg.setSkip(5);
			}
			smap.put(s, moiveStoreService.queryMoive(spg, s));
		}
		req.setAttribute("tmap", map);
		req.setAttribute("smap", smap);
		req.setAttribute("time", Global.newCollectTime);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/v2/index.jsp");
		dispatcher.forward(req, resp);
//		moiveStoreService.
	}
}
