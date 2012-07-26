package com.yuanlong.moiverss.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskOptions.Method;
import com.yuanlong.moiverss.utils.Global;

public class MoiveCollectServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	String date=req.getParameter("date");
	Queue queue = QueueFactory.getQueue("MoiveQueue");
	TaskOptions task= TaskOptions.Builder.withUrl("/tasks/collect").method(Method.POST);
	if(date!=null){
		task.param("date", date);
	}
	queue.add(task);
	Global.collectCount=0;
	Global.lastCollectTime=new Date();
	Global.willCollectCount=0;
	Global.collectList=new ArrayList<String>();
	resp.getWriter().print("Begin Collect Moive!");
}
}
