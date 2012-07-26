package com.yuanlong.moiverss.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskOptions.Method;
import com.yuanlong.moiverss.base.AppContext;
import com.yuanlong.moiverss.service.MoiveCollectService;
import com.yuanlong.moiverss.utils.Global;

public class MoiveInitSearchServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
 
//		MoiveCollectService moiveCollectService =(MoiveCollectService)AppContext.getBean("moiveCollectService");
//		int count=moiveCollectService.importMoive();
//		if (count!=0&&Global.mdlist.size()!=0){
		Queue queue = QueueFactory.getQueue("MoiveQueue");
			queue.add(TaskOptions.Builder.withUrl("/tasks/initsearch").method(Method.POST));
			//		}
			
		resp.getWriter().print("ok");
	}
	
	
}
