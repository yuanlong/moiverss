package com.yuanlong.moiverss.listener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import com.yuanlong.moiverss.base.AppContext;
import com.yuanlong.moiverss.base.VelocityObservable;



public class AppContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent event) {
		AppContext.clear();
	}

	public void contextInitialized(ServletContextEvent event) {
		ApplicationContext springContext = (ApplicationContext) event.getServletContext().getAttribute(
				WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		AppContext.configure(springContext);
		new VelocityObservable().inject();

		
	}

	
}///~;

