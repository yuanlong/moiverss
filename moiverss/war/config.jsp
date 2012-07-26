<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.yuanlong.moiverss.utils.*"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>后台</title>
<style type="text/css">
	body { background: #fff; font-family: "Helvetica Neue", "HelveticaNeue", Helvetica, Arial, "Lucida Grande", sans-serif; font-size: 18px; line-height: 26px; color: #000; position: relative; -webkit-font-smoothing: antialiased; }
</style>
</head>
<body>
<h3>临时采集数据：(上次采集时间：<%=DateUtil.format(Global.lastTempTime,"yyyy-MM-dd hh:mm:ss") %>。临时采集了<%=Global.tempCount %>部)<br/></h3>
<form action="/adm/temp" method="post" >
采集地址：<input name="url" />例：http://www.dyzy.cc/index.asp?KeyWord=HD<br/>
起始页码：<input name="s" />例：1<br/>
结束页码：<input name="e" />例：10<br/>
<input type="hidden" name="type" value="1"/>
<input type="submit" value="提交"/>
</form>
<hr/>
<h3>单独采集：</h3>
<form  action="/adm/temp" method="post" >
采集地址：<input name="url"/> <input type="hidden" name="type" value="2"/> <input type="submit" value="提交"/>
</form>
<hr/>
<h3>手动添加数据：[当前不可用]<br/></h3>
<form action="/adm/add" method="post" >
影片名称：<input /><br/>
图片地址：<input /><br/>
上映时间：<input />例：2012-01-01<br/>
影片地区：<input /><br/>
影片类型：<select name="">
			<option value="剧情片">剧情片</option>	
			<option value="纪录片">纪录片</option>	
			<option value="喜剧片">喜剧片</option>	
			<option value="动作片">动作片</option>	
			<option value="恐怖片">恐怖片</option>	
			<option value="爱情片">爱情片</option>
			<option value="科幻片">科幻片</option>	
			<option value="战争片">战争片</option>
			<option value="动漫频道">动漫频道</option>	
		 </select><br/>
影片导演：<input /><br/>
影片主演：<input /><br/>
视频数据：<textarea rows="8" cols="80"></textarea><br/>
<input type="submit" disabled="disabled" value="添加"/>
</form>

</body>
</html>