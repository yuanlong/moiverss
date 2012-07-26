/**   
 * @{#} Base.java Create on 2011-3-2 ����10:03:27  
 *   
 * Copyright (c) 2011 by JRJ.
 */ 

package com.yuanlong.moiverss.channel.base;

import com.yuanlong.moiverss.vo.MoiveDetail;

/**  
 *   
 *   
 * @history  
 * <PRE>  
 * ---------------------------------------------------------  
 * VERSION       DATE            BY       CHANGE/COMMENT  
 * ---------------------------------------------------------  
 * 1.0           2011-3-2    yuanlong.wang     create  
 * ---------------------------------------------------------  
 * </PRE>  
 *  
 */

public abstract  class DetailBase extends Base {
	protected MoiveDetail mdetail;
	public DetailBase(String url) {
		super();
		this.url = url;
	}


	public DetailBase(String url, String charset) {
		super();
		this.url = url;
		this.charset = charset;
	}



	public MoiveDetail getMdetail() {
		return mdetail;
	}
	public void setMdetail(MoiveDetail mdetail) {
		this.mdetail = mdetail;
	}
	
}
