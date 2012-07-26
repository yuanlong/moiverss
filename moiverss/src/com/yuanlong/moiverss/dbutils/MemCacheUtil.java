package com.yuanlong.moiverss.dbutils;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.yuanlong.moiverss.utils.PageInfo;

public class MemCacheUtil {
	private static final String VCLASS_CACHE_PRO="vclass_";
	private static final String VMOIVE_CACHE_PRO="moive_";
	public static final String VCLASS_CACHE_ALL="_vclass_all_";
	private static final String VTOPMOIVE_CACHE_PRO="mtop_";
	private static final String VPAGE_CACHE_PRO="page_";
	private static final String UNDERLINE="_";
	private static final Logger logger = Logger.getLogger(MemCacheUtil.class
			.getCanonicalName());

	private static MemcacheService keycache = MemcacheServiceFactory
			.getMemcacheService();

	public static void addToCache(String key, Object entity) {
//		logger.log(Level.INFO, "Adding entity to cache");
		keycache.put(key, entity);
	}
	
	public static void addToCache(String key,Object entity,Expiration expir){
		keycache.put(key, entity,expir);
	}
	
	public static void deleteFromCache(String key) {
		logger.log(Level.INFO, "Deleting entity from cache");
		keycache.delete(key);
	}

	public static void deleteFromCache(List<String> keys) {
		keycache.deleteAll(keys);
	}

	public static Object getFromCache(String key) {
		logger.log(Level.INFO, "Searching entity in cache");
		return keycache.get(key);
	}
	
	public static boolean isExists(String key){
		return keycache.contains(key);
	}
	
	public static long inc(String key,long num){
		return keycache.increment(key, num);
	}
	
	public static long inc(String key,long num,long initnum){
		return keycache.increment(key, num,initnum);
	}
	
	public static String getVClassCacheName(String key){
		return VCLASS_CACHE_PRO+key;
	}
	
	public static String getVCacheNameByID(Long id){
		return VMOIVE_CACHE_PRO+id;
	}
	public static String getVCacheNameByTopSize(int topsize){
		return VTOPMOIVE_CACHE_PRO+topsize;
	}
	
	public static String getVCacheNameByPageInfo(PageInfo pg){
		return VPAGE_CACHE_PRO+pg.getCurrentPageNo()+UNDERLINE+pg.getPageSize();
	}
	public static String getVCacheNameByPageInfo(PageInfo pg,String order){
		return VPAGE_CACHE_PRO+pg.getCurrentPageNo()+UNDERLINE+pg.getPageSize()+UNDERLINE+order;
	}
	public static Map<String,Object> getAll(List<String> keys){
		return keycache.getAll(keys);
	}
	
	public static String getVCacheNameByVClassNameAndPage(PageInfo pg,String cname){
		return VPAGE_CACHE_PRO+cname+UNDERLINE+pg.getCurrentPageNo()+UNDERLINE+pg.getPageSize();
	}
}
