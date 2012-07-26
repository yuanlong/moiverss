package com.yuanlong.moiverss.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import com.yuanlong.moiverss.base.AppContext;
import com.yuanlong.moiverss.service.MoiveStoreService;
import com.yuanlong.moiverss.utils.VelocityUtils;
import com.yuanlong.moiverss.vo.MoiveDetail;

public class MoiveDownloadServlet extends HttpServlet  {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String vname=req.getParameter("name");
		
		if(vname==null){
			resp.sendRedirect("/main");
			return;
		}
		MoiveStoreService moiveStoreService = (MoiveStoreService) AppContext
				.getBean("moiveStoreService");
		MoiveDetail md=moiveStoreService.getMoive(vname);
		if (md!=null){
//		resp.reset();
//		resp.setContentType("application/x-msdownload;charset=GBK");
		resp.setContentType("application/octet-stream");
		Map<String,Object> map=new HashMap<String,Object>();
		if(md.getVdata().size()==1){
//			resp.addHeader("Content-Disposition","attachment;filename=\""+getFileName(md.getName(),"qsed")+"\"");
			String fileName = encodeFileName(getFileName(md.getName(),"qsed"), req.getHeader("User-Agent"));
    		
//			resp.setHeader("Content-Disposition", "inline;filename="+getFileName(md.getName(),"qsed"));
			resp.setHeader("Content-Disposition", "inline;filename="+fileName);
//			map.put("v_data", new String(md.getVdata().get(0).getBytes("gb2312"),"ISO8859-1"));
			map.put("v_data",md.getVdata().get(0));
			String content=VelocityUtils.getInstance().mergeToString("vm/qsed.vm",map);
			OutputStream toClient =resp.getOutputStream();
			toClient.write(content.getBytes("gb2312"));
			toClient.flush();
			toClient.close();
//			resp.getWriter().print(content);
		}else{
			map.put("v_pic", md.getPic());
			map.put("v_name", md.getName());
			map.put("v_class", md.getVclass());
			map.put("v_area", md.getArea());
			map.put("v_author", md.getAuthor());
			map.put("v_direct", md.getDirect());
			map.put("v_year", md.getYear());
			map.put("v_datalist", md.getVdata());
//			resp.addHeader("Content-Disposition","attachment;filename=\""+getFileName(md.getName(),"txt")+"\"");
			String fileName = encodeFileName(getFileName(md.getName(),"txt"), req.getHeader("User-Agent"));
			resp.setHeader("Content-Disposition", "inline;filename="+fileName);
			
			String content=VelocityUtils.getInstance().mergeToString("vm/qsedtxt.vm",map);
			resp.getWriter().print(content);
		}
		}else{
			resp.sendRedirect("/main");
			return;
		}
		
		
	}
	  /**
     * @param fileName 	The UTF-8 filename.
     * @param userAgent The User-Agent in HTTP request headers.
     * 
     * @return The encoded filename corresponding to different browsers.
     * 
     * @throws IOException
     */
    private String encodeFileName(String fileName, String userAgent) throws IOException {
    	if (null != userAgent && -1 != userAgent.indexOf("MSIE")) {
			return URLEncoder.encode(fileName, "UTF-8");
		} else if (null != userAgent && -1 != userAgent.indexOf("Mozilla")) {
			return "=?UTF-8?B?" + (new String(Base64.encodeBase64(fileName.getBytes("UTF-8")))) + "?=";
		} else {
			return fileName;
		}
	}
	private String getFileName(String name,String fix){
//		try {
//			return new String(name.getBytes("gb2312"),"ISO8859-1").replaceAll("[/\\\\\\*\\.\\|\"<>]", "")+"."+fix;
//		} catch (UnsupportedEncodingException e) {
			return name.replaceAll("[ /\\\\\\*\\.\\|\"<>]", "_")+"."+fix;
//		}
	}
}
