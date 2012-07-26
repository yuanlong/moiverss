package com.yuanlong.moiverss.queue;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuanlong.moiverss.base.AppContext;
import com.yuanlong.moiverss.channel.DyZyList;
import com.yuanlong.moiverss.service.MoiveCollectService;
import com.yuanlong.moiverss.utils.DateUtil;

public class DoMoiveCollectServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		resp.setContentType("text/html;charset=UTF-8");
		String date=req.getParameter("date");
		if(date!=null){
			try{
				Date d=DateUtil.parse(date, DateUtil.DATEFORMAT_YYYY_MM_DD);
				DyZyList.date=d;
			}catch(Exception e){
				DyZyList.date=DateUtil.get8Date();
			}
		}else{
			DyZyList.date=DateUtil.get8Date();
		}
		MoiveCollectService moiveCollectService =(MoiveCollectService)AppContext.getBean("moiveCollectService");
		int count=moiveCollectService.doCollectDyzy();
//		resp.getWriter().println("共采集"+count+"部高清电影！");
//		resp.getWriter().println("共采集0部高清电影！");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}
