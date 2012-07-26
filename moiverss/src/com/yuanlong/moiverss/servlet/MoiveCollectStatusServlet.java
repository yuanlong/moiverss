package com.yuanlong.moiverss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuanlong.moiverss.utils.DateUtil;
import com.yuanlong.moiverss.utils.Global;

public class MoiveCollectStatusServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.print("现在系统时间："
				+ DateUtil.format(new Date(),
						DateUtil.DATEFORMAT_YYYY_MM_DD_HH_MM_SS)
				+ "，上次采集时间："
				+ DateUtil.format(Global.lastCollectTime,
						DateUtil.DATEFORMAT_YYYY_MM_DD_HH_MM_SS) + "。即将采集"+
				Global.willCollectCount+"部！已成功采集"
				+ Global.collectCount + "部高清电影！将被采集的影片有："+Global.collectList);
	}
}
