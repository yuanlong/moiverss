package com.yuanlong.moiverss.utils;

import java.util.Collections;
import java.util.logging.Logger;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;

public class JCacheUtil {

		private static final Logger log = Logger.getLogger(JCacheUtil.class

		.getName());


		private static JCacheUtil instance;

		private Cache cache;


		private JCacheUtil(){

		try{
		CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();

		cache = cacheFactory.createCache(Collections.emptyMap());

		}catch(CacheException e){

		log.severe("Error in creating the cache");

		}

		}


		public static synchronized JCacheUtil getInstance(){

		if(instance==null){

		instance = new JCacheUtil();

		}

		return instance;

		}
}
