package com.yuanlong.moiverss.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuanlong.moiverss.base.AppContext;
import com.yuanlong.moiverss.service.MoiveRssService;

@SuppressWarnings("serial")
public class MoiveQQrssServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		MoiveRssService moiveRssService=(MoiveRssService)AppContext.getBean("moiveRssService");
		resp.setContentType("text/xml;charset=UTF-8");
		try {
			resp.getWriter().print(moiveRssService.generateRssXml("vm/qqrss.vm"));
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("404.html");
		}
	}
}
