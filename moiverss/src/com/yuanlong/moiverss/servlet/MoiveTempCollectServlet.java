package com.yuanlong.moiverss.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskOptions.Method;

public class MoiveTempCollectServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String url=req.getParameter("url");

		String type=req.getParameter("type");
		if(url==null||"".equals(url)){
			resp.getWriter().print("error");
		}else{
			Queue queue = QueueFactory.getQueue("MoiveQueue");
			if("1".equals(type)){
			String start=req.getParameter("s");
			String end=req.getParameter("e");
			queue.add(TaskOptions.Builder.withUrl("/tasks/tempcollect")
					.param("url", url)
					.param("start", start)
					.param("end", end)
					.param("type", "1").method(Method.POST));
			}else{
				queue.add(TaskOptions.Builder.withUrl("/tasks/tempcollect")
						.param("url", url)
						.param("type", "2")
						.method(Method.POST));
			}
		resp.getWriter().print("ok");
			
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/config.jsp");
		dispatcher.forward(req, resp);
	}
}
