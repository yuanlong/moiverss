package com.yuanlong.moiverss.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;

import com.yuanlong.moiverss.base.AppContext;
import com.yuanlong.moiverss.service.MoiveStoreService;
import com.yuanlong.moiverss.utils.PageInfo;
import com.yuanlong.moiverss.vo.MoiveDetail;

public class MoiveSimpleServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int page = 1;
		try {
			String str = req.getParameter("page");
			if (NumberUtils.isNumber(str)) {
				page = Integer.valueOf(str);
			}
		} catch (NumberFormatException e) {
			page = 1;
		}
		PageInfo pg=new PageInfo();
		pg.setPageSize(39);
		pg.setCurrentPageNo(page);
		MoiveStoreService moiveStoreService = (MoiveStoreService) AppContext
				.getBean("moiveStoreService");
		List<MoiveDetail> list = moiveStoreService.queryMoive(pg);
		req.setAttribute("mlist", list);
		req.setAttribute("pgifo", pg);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/simple.jsp");
		dispatcher.forward(req, resp);
	}

	public static void main(String[] args) {
		System.out.println(Integer.valueOf(null));
	}
}
