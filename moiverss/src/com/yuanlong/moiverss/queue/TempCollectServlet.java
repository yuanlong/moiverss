package com.yuanlong.moiverss.queue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuanlong.moiverss.base.AppContext;
import com.yuanlong.moiverss.service.MoiveCollectService;

public class TempCollectServlet  extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String url=req.getParameter("url");
		int type=Integer.valueOf(req.getParameter("type"));
		MoiveCollectService moiveCollectService =(MoiveCollectService)AppContext.getBean("moiveCollectService");
		if(type==1){
		int start=Integer.valueOf(req.getParameter("start"));
		int end=Integer.valueOf(req.getParameter("end"));
		moiveCollectService.collectByFPageTPage(url, start, end);
		}else if(type==2){
			moiveCollectService.collectByUrl(url);
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}
