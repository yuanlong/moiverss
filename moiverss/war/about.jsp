<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/inc/common.inc"%>
<link rel="stylesheet" type="text/css" href="css/main.css" />
<title>关于我们 | 免费高清电影订阅中心</title>
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
						<a href="/search">搜索</a>
					</dd>
					<dd class="news">
						<a href="/about"  class="active">关于我们</a>
					</dd>
					<dd class="news">
						<a href="http://weibo.com/528880906" target="_blank">关注我们</a>
					</dd>
				</dl>
			</div>
			<div class="seven columns maincol" id="posts">
				<div>
					<h3>关于我们！</h3>
					
					<h5><br/>新浪微博： @wyl_leon <br/>邮箱：yuanlong1318#126.com <br/>QQ:757577749</h5>
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