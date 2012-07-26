<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="com.yuanlong.moiverss.vo.*,com.yuanlong.moiverss.utils.*,java.util.*"%>
<%
	List<MoiveDetail> mlist=(List<MoiveDetail> )request.getAttribute("mlist");
	PageInfo pgifo=(PageInfo) request.getAttribute("pgifo");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/inc/common.inc"%>
<link rel="stylesheet" type="text/css" href="css/main.css" />
<script type="text/javascript" src="js/jquery.pager.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/tab.js"></script>
<title>精简模式 | 免费高清电影订阅中心</title>
<meta name="keywords" content="moiverss,dyrss,高清电影,高清电影订阅中心,高清快播" />
<meta name="description" content="免费高清电影订阅网站，致力于提供优质的高清电影快播资源播放地址。" />
</head>
<body>
	<%@ include file="/inc/head.inc"%>
	<div class="container singlerow">
		<div class="row mg15">
			<div class="two columns show-on-desktops" id="main_nav">
				<dl class="nice tabs vertical" id="MainMenu">
					<dd class="home">
						<a href="/simple" id="browser" class="active" title="精简方式浏览">精简 →</a><a
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
					<div class="alert-box">
						精简模式浏览<a href="http://www.36kr.com" class="url">← 返回首页</a>
					</div>
					<br/>
						<dl class="tabs">
							<dd>
								<a href="#pics_list" class="active"><h4 class="subheader">图片精简</h4></a>
							</dd>
							<dd>
								<a href="#title_list" ><h4 class="subheader">标题精简</h4></a>
							</dd>
							
						</dl>

						<ul class="tabs-content">
							<li id="title_listTab" class="items " style="display: none;">
								<ul>
									<% if (mlist.size()==0) {%>
				<h3>呀！怎么还木有影片！~</h3>
				<%}else{					for(MoiveDetail md:mlist){
					String utime=DateUtil.format(md.getUtime(),DateUtil.DATEFORMAT_YYYY_MM_DD_HH_MM_SS);
				%>
									<li><a target="_blank" rel="bookmark"
										href="/detail?id=<%=md.getId()%>" class="tb"><%=md.getName() %></a>
										[<a rel="category tag" title="<%=md.getVclass() %>"
										href="/category?cn=<%=md.getVclass() %>"><%=md.getVclass() %></a>]  <%=utime %></li>
<%}
				} %>
								</ul>
							</li>

							<li id="pics_listTab" class="items active" style="">
								<% if (mlist.size()==0) {%>
				<ul><h3>呀！怎么还木有影片！~</h3></ul>
				<%}else{					for(MoiveDetail md:mlist){
					String utime=DateUtil.format(md.getUtime(),DateUtil.DATEFORMAT_YYYY_MM_DD_HH_MM_SS);
				%>
								<div class="pin" style="top: 0px; left: 0px;">
									<a href="/detail?id=<%=md.getId()%>"
								title="<%=md.getName() %>"><img class="captify"
								src="<%=md.getPic() %>"
								alt="<%=md.getName() %>"/><span>
								<%
									String tempName=StringUtils.toLength(md.getName(),22) ;
								%>
								<%=tempName%></span></a>
								</div>
  								<%}
				} %>
							</li>
						</ul>
				<div class="">
					<script>
						$(document).ready(function() {
							$("#pager").pager({
								pagenumber :
					<%=pgifo.getCurrentPageNo()%>
						,
								pagecount :
					<%=pgifo.getTotalPage()%>
						,
								buttonClickCallback : PageClick
							});
						});
						PageClick = function(pageclickednumber) {
							location = "/simple?page="
									+ pageclickednumber;
							}
						;
						$(function(){
							$('.captify').mouseover(function() {
								$(this).animate({
									opacity : ".6"
								})
							}).mouseout(function() {
								$(this).animate({
									opacity : "1"
								})
							});
						});
					</script>
					<div id="pager"></div>
				</div>
			</div>
			<%@ include file="/inc/right.inc"%>
		</div>
	</div>
	<%@ include file="/inc/foot.inc"%>
</body>
</html>