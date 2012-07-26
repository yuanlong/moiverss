package com.yuanlong.moiverss.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.memcache.Expiration;
import com.yuanlong.moiverss.base.PMF;
import com.yuanlong.moiverss.dbutils.MemCacheUtil;
import com.yuanlong.moiverss.dbutils.MoiveClassCounterUtils;
import com.yuanlong.moiverss.search.SearchJanitor;
import com.yuanlong.moiverss.utils.PageInfo;
import com.yuanlong.moiverss.vo.MoiveDetail;
import com.yuanlong.moiverss.vo.MoiveSearch;

public class MoiveStoreServiceImpl implements MoiveStoreService {

	private void saveMoiveSearch(MoiveSearch ms) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.makePersistent(ms);
	}

	/**
	 * 保存影片
	 */
	@Override
	public void saveMoive(MoiveDetail moive) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		MoiveDetail md = pm.makePersistent(moive);
		MoiveClassCounterUtils.addAndGet(md.getVclass(), 1);
		MoiveSearch ms = new MoiveSearch(moive.getName(), moive.getId());
		saveMoiveSearch(ms);
	}

	private Key getKeyForId(Long id) {
		return KeyFactory.createKey(MoiveDetail.class.getSimpleName(), id);
	}

	/**
	 * 是否存在影片
	 */
	@Override
	public boolean isExist(String name) {
		if (getMoive(name) != null) {
			return true;
		}
		return false;
	}

	/**
	 * 根据指定id获取影片
	 */
	public MoiveDetail getMoiveById(Long id) {
		Object obj = MemCacheUtil.getFromCache(MemCacheUtil
				.getVCacheNameByID(id));
		if (obj != null) {
			return (MoiveDetail) obj;
		} else {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			MoiveDetail moive = null;
			try {
				moive = pm.getObjectById(MoiveDetail.class,
						KeyFactory.keyToString(getKeyForId(id)));
			} catch (JDOObjectNotFoundException e) {
				moive = null;
			}
			if (moive != null) {
				MemCacheUtil.addToCache(MemCacheUtil.getVCacheNameByID(id),
						moive);
			}
			return moive;
		}
	}

	/**
	 * 根据名字获取
	 */
	@Override
	public MoiveDetail getMoive(String name) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		MoiveDetail moive = null;
		Query query = pm.newQuery(MoiveDetail.class);
		query.declareParameters("String myKey");
		query.setFilter("name==myKey");
		List<MoiveDetail> list = (List<MoiveDetail>) query.execute(name);
		if (list != null && !list.isEmpty()) {
			moive = list.get(0);
		}
		return moive;
	}

	/**
	 * 获取top size 部电影
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MoiveDetail> queryMoive(int size) {
		List<Long> idlist = getTopMoiveIDFromCache(size);
		if (idlist != null) {
			return getMoiveDetailListByIDSFromCache(idlist);
		} else {
			PersistenceManager pm = PMF.get().getPersistenceManager();

			Query query = pm.newQuery(MoiveDetail.class);
			query.setOrdering("utime desc");
			query.setRange(0, size);
			List<MoiveDetail> list = (List<MoiveDetail>) query.execute();

			if (list != null && !list.isEmpty()) {
				idlist = new ArrayList<Long>();
				for (MoiveDetail md : list) {
					idlist.add(md.getId());
				}
				MemCacheUtil.addToCache(
						MemCacheUtil.getVCacheNameByTopSize(size), idlist,
						Expiration.byDeltaSeconds(1200));
				checkCache(list, idlist);
			}
			return list;
		}
	}

	private List<Long> getTopMoiveIDFromCache(int size) {
		Object obj = MemCacheUtil.getFromCache(MemCacheUtil
				.getVCacheNameByTopSize(size));
		if (obj != null)
			return (List<Long>) obj;
		return null;
	}

	@Override
	public List<MoiveDetail> queryMoive(PageInfo page) {
		
		return queryMoiveO(page, "utime");

	}

	/**
	 * 根据id列表获取
	 * 
	 * @param list
	 * @return
	 */
	public List<MoiveDetail> getMoiveDetailListByIDSFromCache(List<Long> list) {
		List<MoiveDetail> mlist = new ArrayList<MoiveDetail>();
		for (Long id : list) {
			MoiveDetail md = getMoiveById(id);
			if (md != null) {
				mlist.add(md);
			}
		}
		return mlist;
	}

	/**
	 * 检验memcache
	 * 
	 * @param mlist
	 * @param list
	 */
	private void checkCache(List<MoiveDetail> mlist, List<Long> list) {
		List<String> keys = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			Long id = list.get(i);
			keys.add(MemCacheUtil.getVCacheNameByID(id));
		}
		Map<String, Object> cachemap = MemCacheUtil.getAll(keys);
		for (int i = 0; i < mlist.size(); i++) {
			if (!cachemap.containsKey(keys.get(i))
					|| cachemap.get(keys.get(i)) == null) {
				MoiveDetail md = mlist.get(i);
				MemCacheUtil.addToCache(
						MemCacheUtil.getVCacheNameByID(md.getId()), md);
			}
		}
	}

	/**
	 * 获取全部总数
	 * 
	 * @return
	 */
	public int getCount() {
		return MoiveClassCounterUtils.getAllCount();
	}

	/**
	 * 关键词搜索影片
	 */
	@Override
	public List<MoiveDetail> queryMoive(String key, PageInfo page) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		/*
		 * Query query = pm.newQuery(MoiveDetail.class);
		 * query.declareParameters("String myKey, String myKey2"); //
		 * query.setFilter("name.matches('myKey.*')");
		 * query.setFilter("name >= myKey && name < myKey2"); //
		 * query.setOrdering("utime desc");
		 * query.setRange(page.getCurrentPageStart(),
		 * page.getCurrentPageStart()+page.getPageSize()); List<MoiveDetail>
		 * list = (List<MoiveDetail>) query.execute(key,key+"\uFFFFD"); return
		 * list;
		 */
		List<MoiveSearch> mslist = SearchJanitor.searchMoiveSearchEntries(key,
				pm, page);
		List<MoiveDetail> list = new ArrayList<MoiveDetail>();
		if (mslist != null) {
			for (MoiveSearch ms : mslist) {
				MoiveDetail md = getMoiveById(ms.getMid());
				if (md != null) {
					list.add(md);
				}
			}
			if (mslist.size() == page.getPageSize() + 1) {
				page.setHasNext(true);
			} else {
				page.setHasNext(false);
			}
		}
		return list;
	}

	/**
	 * 导出所有影片
	 */
	@Override
	public List<MoiveDetail> exportMoive() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(MoiveDetail.class);
		List<MoiveDetail> list = (List<MoiveDetail>) query.execute();
		return list;
	}

	/**
	 * 获取指定分类影片
	 */
	@Override
	public List<MoiveDetail> queryMoive(PageInfo pg, String cname) {
		pg.setTotalCount(MoiveClassCounterUtils.getCounterByName(cname));
		List<Long> list = (List<Long>) MemCacheUtil.getFromCache(MemCacheUtil
				.getVCacheNameByVClassNameAndPage(pg, cname));
		if (list != null) {
			return getMoiveDetailListByIDSFromCache(list);
		} else {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			MoiveDetail moive = null;
			Query query = pm.newQuery(MoiveDetail.class);
			query.declareParameters("String myKey");
			query.setFilter("vclass==myKey");
			query.setOrdering("utime desc");
			query.setRange(pg.getCurrentPageStart(), pg.getCurrentPageStart()
					+ pg.getPageSize());
			List<MoiveDetail> mlist = (List<MoiveDetail>) query.execute(cname);
			if (mlist != null && !mlist.isEmpty()) {
				list = new ArrayList<Long>();
				for (MoiveDetail md : mlist) {
					list.add(md.getId());
				}
				MemCacheUtil.addToCache(MemCacheUtil
						.getVCacheNameByVClassNameAndPage(pg, cname), list,
						Expiration.byDeltaSeconds(1200));
				checkCache(mlist, list);
			}
			return mlist;
		}
	}

	@Override
	public List<MoiveDetail> queryMoiveO(PageInfo page, String order) {
		List<Long> list = (List<Long>) MemCacheUtil.getFromCache(MemCacheUtil
				.getVCacheNameByPageInfo(page,order));
		page.setTotalCount(getCount());
		if (list != null) {
			return getMoiveDetailListByIDSFromCache(list);
		} else {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Query query = pm.newQuery(MoiveDetail.class);
			query.setOrdering(order+" desc");
			query.setRange(page.getCurrentPageStart(),
					page.getCurrentPageStart() + page.getPageSize());
			List<MoiveDetail> mlist = (List<MoiveDetail>) query.execute();
			if (mlist != null && !mlist.isEmpty()) {
				list = new ArrayList<Long>();
				for (MoiveDetail md : mlist) {
					list.add(md.getId());
				}
				MemCacheUtil.addToCache(
						MemCacheUtil.getVCacheNameByPageInfo(page,order), list,
						Expiration.byDeltaSeconds(1200));
				checkCache(mlist, list);
			}
			return mlist;
		}
	}

}
