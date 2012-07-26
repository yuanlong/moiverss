<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/inc/common.inc"%>
<link rel="stylesheet" type="text/css" href="css/main.css" />
<title>免费高清电影订阅中心</title>
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
						<a href="/simple" id="browser" title="精简方式浏览">精简 →</a><a
							href="/main">首页</a>
					</dd>
					<dd class="news">
						<a href="/category" class="active">分类浏览</a>
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
				<div>
					<div>
						<img src="images/category_01.gif" width="623" height="53" alt="" />
					</div>
					<div>
						<img src="images/category_02.png" width="52" height="77" alt="" />
						<a href="/category?cn=剧情片" title="剧情片"><img class="category"
							src="images/category_03.gif" width="221" height="77" alt="剧情片" />
						</a> <a href="/category?cn=纪录片" title="纪录片"><img class="category"
							src="images/category_04.gif" width="261" height="77" alt="纪录片" />
						</a> <img src="images/category_05.gif" width="89" height="77" alt="" />
					</div>
					<div>
						<img src="images/category_06.gif" width="97" height="89" alt="" />
						<a href="/category?cn=喜剧片" title="喜剧片"><img class="category"
							src="images/category_07.gif" width="289" height="89" alt="喜剧片" /></a>
						<a href="/category?cn=动作片" title="动作片"><img class="category"
							src="images/category_08.gif" width="237" height="89" alt="动作片" /></a>
					</div>
					<div>
						<a href="/category?cn=战争片" title="战争片"><img class="category"
							src="images/category_09.gif" width="170" height="116" alt="战争片" />
						</a> <a href="/category?cn=爱情片" title="爱情片"><img class="category"
							src="images/category_10.gif" width="338" height="116" alt="爱情片" /></a>
						<img src="images/category_11.gif" width="115" height="116" alt="" />
					</div>
					<div>
						<a href="/category?cn=恐怖片" title="恐怖片"><img class="category"
							src="images/category_12.gif" width="201" height="73" alt="恐怖片" /></a>
						<img src="images/category_13.gif" width="223" height="73" alt="" />
						<a href="/category?cn=动漫频道" title="动漫频道"><img class="category"
							src="images/category_14.gif" width="199" height="73" alt="动漫频道" /></a>
					</div>
					<div>
						<img src="images/category_15.gif" width="200" height="69" alt="" />
						<a href="/category?cn=科幻片" title="科幻片"><img class="category"
							src="images/category_16.png" width="224" height="69" alt="科幻片" /></a>
						<img src="images/category_17.gif" width="199" height="69" alt="" />
					</div>
					<div>
						<img src="images/category_01.gif" width="623" height="53" alt="" />
					</div>
				</div>
			</div>
			<script>
			$(function(){
				$('.category').each(function() {
					$(this).animate({
						opacity : ".6"
					});
				});
				$('.category').mouseover(function() {
					$(this).animate({
						opacity : "1"
					})
				}).mouseout(function() {
					$(this).animate({
						opacity : ".6"
					})
				});
			});
			</script>
			<%
				wb_h = 204;
			%>
			<%@ include file="/inc/right.inc"%>
		</div>
	</div>
	<%@ include file="/inc/foot.inc"%>
</body>
</html>