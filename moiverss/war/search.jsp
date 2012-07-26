<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/inc/common.inc"%>
<link rel="stylesheet" type="text/css" href="css/main.css" />
<title>搜索 | 免费高清电影订阅中心</title>
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
						<a href="/category">分类浏览</a>
					</dd>
					<dd class="news">
						<a href="/search" class="active">搜索</a>
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
				<div style="height:547px">
					<form class="" style="width: 500px; margin-left: -250px;"
						id="homeSearch">
						<div id="searchBar">
							<div class="searchBar_left"></div>
							<div class="searchBar_inner"></div>
							<div class="searchBar_right"></div>
							<div class="searchBar_inner_left"></div>
							<div class="searchBar_inner_inner"></div>
							<div class="searchBar_inner_right"></div>
						</div>
						<div id="searchBar_input">
							<span id="searchBar_hint" class="hint"
								data-translate-text="SEARCH_FOR_MUSIC">搜索影片</span> <span
								id="searchBar_precomplete" class="hint"></span> <input id="searchInput"
								type="text" name="q"
								class="search autocomplete ui-autocomplete-input" maxlength="64"
								autocomplete="off"/>
						</div>
						<button id="searchButton" type="button"></button>
					</form>
				</div>
			</div>
			<script>
				$(function(){
					$("#searchInput").focus(function(){
						$("#searchBar_hint").empty();
					}).blur(function(){
						if($("#searchInput").val()==""){
							$("#searchBar_hint").html("搜索影片");
						}
					});
					
					$("#searchButton").click(function(){
						if($("#searchInput").val()==""){
							$("#searchInput").focus();
						}else{
							$("#homeSearch").submit();
						}
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