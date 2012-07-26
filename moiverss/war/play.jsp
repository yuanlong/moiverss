<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.yuanlong.moiverss.vo.*,com.yuanlong.moiverss.utils.*,java.util.*" %>
<%
	MoiveDetail md=(MoiveDetail) request.getAttribute("moive");
if(md==null){
	response.sendRedirect("/main");
}
%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/inc/common.inc"%>
<link rel="stylesheet" type="text/css" href="css/main.css" />
<script type="text/javascript" src="js/main.js"></script>
<title>在线播放：<%=md.getName() %> | 免费高清电影订阅中心</title>
<meta name="keywords" content="<%=md.getName() %>,<%=md.getName() %>在线播放,moiverss,dyrss,高清电影,高清电影订阅中心,高清快播" />
<meta name="description" content="免费高清电影订阅网站，致力于提供优质的高清电影快播资源播放地址。" />
</head>
<body>
	<%@ include file="/inc/head.inc"%>
	<div class="container singlerow">
		<div class="row mg15">
			<div class="two columns show-on-desktops" id="main_nav">
				<dl class="nice tabs vertical" id="MainMenu">
					<dd class="home">
						<a href="/simple" id="browser" title="精简方式浏览">精简 →</a><a
							href="/main">首页</a>
					</dd>
					<dd class="news">
						<a href="/category">分类浏览</a>
					</dd>
					<dd class="news">
						<a href="/search">搜索</a>
					</dd>
					<dd class="news">
						<a href="/detail?id=240047">蓝光高清合辑</a>
					</dd>
					<dd class="news">
						<a href="/about">关于我们</a>
					</dd>
					<dd class="news">
						<a href="http://weibo.com/528880906" target="_blank">关注我们</a>
					</dd>
				</dl>
			</div>
			<div class="seven columns maincol" id="posts">
			<div class="alert-box"> 当前位置： 在线播放 › <%=md.getName() %> <a href="/main" class="url">← 返回首页</a></div>
				<div>
				<object classid='clsid:F3D0D36F-23F8-4682-A195-74C92B03D4AF' id='QvodPlayer' width='680' height='497' onError="javascript:if(window.confirm('请您先安装QvodPlayer软件,然后刷新本页才可以正常播放.')){window.open('http://www.kuaibo.com')}else{self.location='http://www.kuaibo.com'}">
<PARAM NAME='URL' VALUE='<%=MoiveUrlCheckUtil.checkUrl( md.getVdata().get(0))%>'>
<PARAM NAME='Showcontrol' VALUE=1>
<PARAM NAME='AutoPlay' VALUE='1'>
 <param name='QvodAdUrl' value='http://www.dyrss.com/ad.html'> 
<embed URL='<%=MoiveUrlCheckUtil.checkUrl( md.getVdata().get(0))%>' type='application/qvod-plugin' width='680' height='497' ></embed>
</object>
				</div>
			</div>
			<%
				wb_h = 204;
			%>
			<%@ include file="/inc/right.inc"%>
		</div>
	</div>
	<%@ include file="/inc/foot.inc"%>
</body>
</html>