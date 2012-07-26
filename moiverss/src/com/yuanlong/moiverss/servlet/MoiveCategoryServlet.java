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
import com.yuanlong.moiverss.dbutils.MoiveClassCounterUtils;
import com.yuanlong.moiverss.service.MoiveStoreService;
import com.yuanlong.moiverss.utils.PageInfo;
import com.yuanlong.moiverss.vo.MoiveDetail;

public class MoiveCategoryServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String cn=req.getParameter("cn");
		if(cn==null||MoiveClassCounterUtils.getByName(cn)==null){
			req.getRequestDispatcher("/categorymain.jsp").forward(req, resp);
		}else{
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
			pg.setCurrentPageNo(page);
			MoiveStoreService moiveStoreService = (MoiveStoreService) AppContext
					.getBean("moiveStoreService");
			List<MoiveDetail> list = moiveStoreService.queryMoive(pg,cn);
			req.setAttribute("mlist", list);
			req.setAttribute("pgifo", pg);
//			req.setAttribute("cname", cn);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/category.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
