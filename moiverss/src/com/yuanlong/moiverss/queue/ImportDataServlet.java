package com.yuanlong.moiverss.queue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuanlong.moiverss.base.AppContext;
import com.yuanlong.moiverss.service.MoiveCollectService;
import com.yuanlong.moiverss.utils.Global;


public class ImportDataServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		resp.setContentType("text/html;charset=UTF-8");
//		MoiveCollectService moiveCollectService =(MoiveCollectService)AppContext.getBean("moiveCollectService");
//		int count=moiveCollectService.importMoive();
//		Global.status=100;
//		resp.getWriter().println("共导入"+count+"部高清电影！");
		
		/*MoiveStoreService moiveStoreService =(MoiveStoreService)AppContext.getBean("moiveStoreService");
		MoiveDetail md=Global.getNextMoive();
		if(md!=null){
		if(!moiveStoreService.isExist(md.getName())){
			moiveStoreService.saveMoive(md);
			}
		}
		resp.getWriter().print(Global.index);*/
		
//			moiveStoreService.saveMoive(md);
//			count++;
//	}	
//		try {
//			Thread.sleep(10000);
//			Global.status=100;
//			System.out.println("======================");
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
	
}
