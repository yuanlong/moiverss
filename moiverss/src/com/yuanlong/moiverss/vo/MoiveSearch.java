package com.yuanlong.moiverss.vo;

import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.yuanlong.moiverss.search.SearchJanitor;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class MoiveSearch {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	private Set<String> fts;
	@Persistent
	private String mname;
	@Persistent
	private Long mid;

	public MoiveSearch(String mname, Long mid) {
		this.mid = mid;
		this.mname = mname;
		this.fts = new HashSet<String>();

		SearchJanitor.updateFTSStuffForMoiveSearchEntry(this);
	}

	public Set<String> getFts() {
		return fts;
	}

	public void setFts(Set<String> fts) {
		this.fts = fts;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public Long getMid() {
		return mid;
	}

	public void setMid(Long mid) {
		this.mid = mid;
	}

	public Long getId() {
		return id;
	}

}
