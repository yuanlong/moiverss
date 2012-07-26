package com.yuanlong.moiverss.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.KeyFactory;
import com.yuanlong.moiverss.base.AppContext;
import com.yuanlong.moiverss.base.PMF;
import com.yuanlong.moiverss.service.MoiveStoreService;
import com.yuanlong.moiverss.utils.MD5Util;
import com.yuanlong.moiverss.vo.MoiveDetail;

public class MoiveIndexServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		MoiveStoreService moiveStoreService =(MoiveStoreService)AppContext.getBean("moiveStoreService");
//		List<MoiveDetail> list=moiveStoreService.queryMoive(10);
//		StringBuffer sb=new StringBuffer();
//		for(MoiveDetail md:list){
//			sb.append(md.toString()).append("\n");
//		}
//		String name=req.getParameter("name");
//		resp.setContentType("text/html;charset=UTF-8");
//		PrintWriter writer=resp.getWriter();
//		writer.println(moiveStoreService.getCount());
//		writer.println(moiveStoreService.getMoive(name));
		/*PersistenceManager pm = PMF.get().getPersistenceManager();
		MoiveDetail moive = null;
		try {
			moive = pm.getObjectById(MoiveDetail.class,KeyFactory.keyToString(KeyFactory.createKey(MoiveDetail.class.getSimpleName(),
					MD5Util.MD5(name))));
		}catch (Exception e) {
		}
		writer.println(moive);*/
		
		resp.sendRedirect("/main");
	}
}
