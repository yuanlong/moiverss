package com.yuanlong.moiverss.service;

import java.util.List;

import com.google.appengine.api.datastore.Cursor;
import com.yuanlong.moiverss.utils.PageInfo;
import com.yuanlong.moiverss.vo.MoiveDetail;

public interface MoiveStoreService {
	
	public void saveMoive(MoiveDetail moive);
	
	public boolean isExist(String name);
	
	public MoiveDetail getMoiveById(Long id);
	
	public MoiveDetail getMoive(String name);
	
	public List<MoiveDetail> queryMoive(int size);
	
	public List<MoiveDetail> queryMoive(PageInfo page);
	public List<MoiveDetail> queryMoiveO(PageInfo page,String order);
	public List<MoiveDetail> queryMoive(String key,PageInfo page);
	
	public List<MoiveDetail> exportMoive();
	
	public int getCount();

	public List<MoiveDetail> queryMoive(PageInfo pg, String cname);
}
