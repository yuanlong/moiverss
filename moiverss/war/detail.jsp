<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="com.yuanlong.moiverss.vo.*,com.yuanlong.moiverss.utils.*,java.util.*"%>
<%
	MoiveDetail md = (MoiveDetail) request.getAttribute("moive");
	if (md == null) {
		response.sendRedirect("/main");
	}
	String utime = DateUtil.format(md.getUtime(),
			DateUtil.DATEFORMAT_YYYY_MM_DD_HH_MM_SS);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/inc/common.inc"%>
<link rel="stylesheet" type="text/css" href="css/main.css" />
<script type="text/javascript" src="js/jquery.pager.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<title><%=md.getName()%> | 免费高清电影订阅中心</title>
<meta name="keywords"
	content="<%=md.getName()%>,moiverss,dyrss,高清电影,高清电影订阅中心,高清快播" />
<meta name="description"
	content="<%=md.getName()%> | 免费高清电影订阅网站，致力于提供优质的高清电影快播资源播放地址。" />
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
				<div class="alert-box">
					当前位置： 影片详情 ›
					<%=md.getName()%>
					<a href="/main" class="url">← 返回首页</a>
				</div>
				<article class="posts">
				<h3>
					<a target="_blank" href="/detail?id=<%=md.getId()%>"><%=md.getName()%></a>
				</h3>
				<div class="postmeta">
					更新于 <strong><%=utime%></strong>

				</div>
				<div></div>
				<div class="row">
					<div class="three columns">
						<div class="thumbnail hide-on-phones">
							<a href="/detail?id=<%=md.getId()%>" title="<%=md.getName()%>"><img
								height="200" width="150" src="<%=md.getPic()%>"
								alt="<%=md.getName()%>" /></a>
						</div>
					</div>
					<div class="nine columns">
						<p>
							<strong>影片名称: </strong><%=md.getName()%>
						</p>
						<p>
							<strong>影片栏目: </strong><%=md.getVclass()%>
						</p>
						<p>
							<strong>影片地区: </strong><%=md.getArea()%>
						</p>
						<p>
							<strong>影片主演: </strong><%=md.getAuthor()%>
						</p>
						<p>
							<strong>影片导演: </strong><%=md.getDirect()%>
						</p>
						<p>
							<strong>发行日期: </strong><%=md.getYear()%>
						</p>
						<div class="download">
							<div class="readmore">
								<a class="button" href="/play?id=<%=md.getId()%>" target="play">在线播放</a>
							</div>
							<div class="readmore">
								<a class="button" href="/down?name=<%=md.getName()%>">下载种子</a>
							</div>
						</div>
					</div>
					<hr/>
						<dl class="tabs">
							<dd>
								<a class="active" class="active"><h4 class="subheader">播放地址</h4></a>
							</dd>
						</dl>

						<ul class="tabs-content">
							<li id="title_listTab" class="items active" style="">
								<ul>
									<%
										List<String> mlist = md.getVdata();
										if (mlist.size() == 0) {
									%>
									<h3>呀！怎么还木有播放地址！~</h3>
									<%
										} else {
											for (String vurl : mlist) {
									%>
									<li><a target="_blank" rel="bookmark"
										href="/play?id=<%=md.getId()%>" class="tb"><%=MoiveUrlCheckUtil.checkUrl(vurl)%></a></li>
									<%
										}
										}
									%>
								</ul>
							</li>
						</ul>
					
				</div>
				<hr>
				</article>
			</div>
			<%
				wb_h = 203;
			%>
			<%@ include file="/inc/right.inc"%>
		</div>
	</div>
	<%@ include file="/inc/foot.inc"%>
</body>
</html>