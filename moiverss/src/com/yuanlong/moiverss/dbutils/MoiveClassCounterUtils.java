/* Copyright (c) 2009 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yuanlong.moiverss.dbutils;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.yuanlong.moiverss.base.PMF;
import com.yuanlong.moiverss.vo.MoiveClassCounter;

public class MoiveClassCounterUtils {

  public static Key getKeyForName(String name) {
    return KeyFactory.createKey(MoiveClassCounter.class.getSimpleName(), name);
  }

  public static int getAllCount(){
	  List<String> list=getAllCounterName();
	  int count=0;
	  for(String name:list){
		  count+= getCounterByName(name);
	  }
	 /* List<MoiveClassCounter> list = getAllCounter();
	  for(MoiveClassCounter mc:list){
		  count+=mc.getCount();
	  }*/
	  return count;
  }

public static List<MoiveClassCounter> getAllCounter() {
	PersistenceManager pm = PMF.get().getPersistenceManager();
	  Query query = pm.newQuery(MoiveClassCounter.class);
	  List<MoiveClassCounter> list = (List<MoiveClassCounter>) query.execute();
	return list;
}
  public static List<String> getAllCounterName(){
	  Object obj=MemCacheUtil.getFromCache(MemCacheUtil.VCLASS_CACHE_ALL);
	  if(obj!=null){
		  return (List<String>) obj;
	  }
	  List<String> slist=new ArrayList<String>();
	  List<MoiveClassCounter> list=getAllCounter();
	  for(MoiveClassCounter mc:list){
		  slist.add(mc.getClazz());
	  }
	  MemCacheUtil.addToCache(MemCacheUtil.VCLASS_CACHE_ALL, slist);
	  return slist;
  }
  public static MoiveClassCounter getByName(String name) {
    PersistenceManager pm = PMF.get().getPersistenceManager();
    MoiveClassCounter counter = null;

    try {
      counter = pm.getObjectById(MoiveClassCounter.class,
          KeyFactory.keyToString(getKeyForName(name)));
    } catch (JDOObjectNotFoundException e) {
      counter = null;
    }
    return counter;
  }
  
  public static int getCounterByName(String name){
	  if (MemCacheUtil.isExists(MemCacheUtil.getVClassCacheName(name))){
		  return (Integer) MemCacheUtil.getFromCache(MemCacheUtil.getVClassCacheName(name));
	  }else{
		  MoiveClassCounter counter=getByName(name);
		  if (counter!=null){
			  MemCacheUtil.addToCache(MemCacheUtil.getVClassCacheName(counter.getClazz()), counter.getCount());
			  return counter.getCount();
		  }else{
			  return 0;
		  }
	  }
  }

  public static int addAndGet(String name, int delta) {
    PersistenceManager pm = PMF.get().getPersistenceManager();

    MoiveClassCounter counter = null;
    boolean flag=false;
    try {
      pm.setDetachAllOnCommit(true);
      pm.currentTransaction().begin();
      try {
        counter = pm.getObjectById(MoiveClassCounter.class,
            KeyFactory.keyToString(getKeyForName(name)));
        counter.add(delta);
      } catch (JDOObjectNotFoundException e) {
        counter = new MoiveClassCounter(name);
        counter.add(delta);
        pm.makePersistent(counter);
        flag=true;
      }
      pm.currentTransaction().commit();
      if(counter!=null){
	      if (MemCacheUtil.isExists(MemCacheUtil.getVClassCacheName(counter.getClazz()))){
	    	  MemCacheUtil.inc(MemCacheUtil.getVClassCacheName(counter.getClazz()), 1);
	      }else{
	    	  MemCacheUtil.addToCache(MemCacheUtil.getVClassCacheName(counter.getClazz()), counter.getCount());
	      }
	      if(flag){
		      List<String> slist=getAllCounterName();
		      if (!slist.contains(counter.getClazz())){
		    	  slist.add(counter.getClazz());
		      }
		      MemCacheUtil.addToCache(MemCacheUtil.VCLASS_CACHE_ALL, slist);
	      }
      }
    } finally {
      if (pm.currentTransaction().isActive()) {
        pm.currentTransaction().rollback();
      }
    }
    return counter.getCount();
  }

  public static void reset(String name) {
    PersistenceManager pm = PMF.get().getPersistenceManager();

    try {
      pm.currentTransaction().begin();
      try {
    	  MoiveClassCounter counter = pm.getObjectById(MoiveClassCounter.class,
            KeyFactory.keyToString(getKeyForName(name)));
        pm.deletePersistent(counter);
        MemCacheUtil.deleteFromCache(MemCacheUtil.getVClassCacheName(counter.getClazz()));
      } catch (JDOObjectNotFoundException e) {
        return;
      }
      pm.currentTransaction().commit();
    } finally {
      if (pm.currentTransaction().isActive()) {
        pm.currentTransaction().rollback();
      }
    }
  }
}
