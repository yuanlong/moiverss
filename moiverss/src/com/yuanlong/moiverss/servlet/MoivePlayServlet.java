package com.yuanlong.moiverss.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;

import com.yuanlong.moiverss.base.AppContext;
import com.yuanlong.moiverss.service.MoiveStoreService;
import com.yuanlong.moiverss.vo.MoiveDetail;

public class MoivePlayServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Long id = 0L;
		try {
			String str = req.getParameter("id");
			if (NumberUtils.isNumber(str)) {
				id = Long.valueOf(str);
			}else{
				resp.sendRedirect("/main");
				return ;
			}
		} catch (NumberFormatException e) {
			resp.sendRedirect("/main");
			return;
		}
		if(id==0){
			resp.sendRedirect("/main");
			return ;
		}
		MoiveStoreService moiveStoreService = (MoiveStoreService) AppContext
				.getBean("moiveStoreService");
		MoiveDetail md=moiveStoreService.getMoiveById(id);
		if (md!=null){
			req.setAttribute("moive", md);
			RequestDispatcher disp=req.getRequestDispatcher("/play.jsp");
			disp.forward(req, resp);
		}else{
			resp.sendRedirect("/main");
			return;
		}
	}
}
