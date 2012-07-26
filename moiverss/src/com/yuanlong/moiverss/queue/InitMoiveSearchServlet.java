package com.yuanlong.moiverss.queue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuanlong.moiverss.base.AppContext;
import com.yuanlong.moiverss.base.PMF;
import com.yuanlong.moiverss.service.MoiveStoreService;
import com.yuanlong.moiverss.vo.MoiveDetail;
import com.yuanlong.moiverss.vo.MoiveSearch;

public class InitMoiveSearchServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		MoiveStoreService moiveStoreService = (MoiveStoreService) AppContext
				.getBean("moiveStoreService");
		List<MoiveDetail> mlist=moiveStoreService.exportMoive();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<MoiveSearch> mslist=new ArrayList<MoiveSearch>();
		for(MoiveDetail md:mlist){
			mslist.add(new MoiveSearch(md.getName(), md.getId()));
		}
		pm.makePersistentAll(mslist);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}
