package com.yuanlong.moiverss.base;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;



public class AppContext {

	// -----------------------------------------------------------------------------------------------------------------------------------------------//

	private static final String KEY_SPRING_CTX = "KEY_SPRING_CTX";
	/*
	 * 程序执行的上下文
	 */
	private static final Map<String, Object> context = new HashMap<String, Object>();

	public static void put(String k, Object v) {
		Assert.notNull(k);
		context.put(k, v);
	}

	public static void clear() {
		context.clear();
	}

	public static void configure(ApplicationContext springContext) {
		put(KEY_SPRING_CTX, springContext);
	}
	
	public static Object getBean(String beanname){
		ApplicationContext context = getSpringCtx();
		return context.getBean(beanname);
	}

	public static ApplicationContext getSpringCtx() {
		Object ctx = context.get(KEY_SPRING_CTX);
		Assert.notNull(ctx);
		Assert.isAssignable(ApplicationContext.class, ctx.getClass());
		return (ApplicationContext) ctx;
	}
}
