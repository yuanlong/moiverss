package com.yuanlong.moiverss.vo;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable(identityType=IdentityType.APPLICATION, detachable="true")
public class MoiveClassCounter {
	@PrimaryKey
	private String clazz;

	@Persistent
	private int count;

	public MoiveClassCounter(String clazz) {
		this.clazz = clazz;
		this.count = 0;
	}

	public int getCount() {
	    return count;
	  }

	  public void add(int delta) {
	    count += delta;
	  }

	  public String getClazz() {
		  return clazz;
//	    return KeyFactory.stringToKey(clazz).getName();
	  }
	
}
