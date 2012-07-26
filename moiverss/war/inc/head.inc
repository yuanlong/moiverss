<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
int wb_h=799;
%>
<nav class="show-on-phones"> <a href="/main" style=""
		class="button black nice">返回首页</a></nav>
	<div id="krBar" class="container hide-on-phones">
		<div class="row" id="kb_inner">
			<div id="topmenu" class="eight columns">
				
			</div>
			<div class="four columns">
				<div class="right" style="margin-top: 4px">
				<a data-title="@wyl_leon" class="small button radius" rel="weibo" href="http://weibo.com/528880906" target="_blank">+ 关注</a>
					<a data-title="/rss"
						href="/rss" class="small red button radius"
						rel="shareit" target="_blank">+ 订阅</a>
				</div>
			</div>
		</div>
	</div>
	<div id="header" role="main" class="container">
		<header class="row"> <hgroup class="two columns">
		<h1>
			<a rel="home" href="/main"><span
				style="color: #ff0000;">Moive</span><span style="color: #ffa500;">R</span><span
				style="color: #0000ff;">SS</span></a>
		</h1>
		<h5 class="subheader">高清电影订阅中心</h5>
		</hgroup>
		<div class="three columns fr">
			<form class="nice hide-on-phones" action="/search"
				id="searchform" method="get" role="search">
				<input type="text" placeholder="搜索" autocomplete="off"
					x-webkit-speech="" class="small nice fr input-text oversize"
					style="margin-left: 40px; margin-top: 30px;width: 280px;" value="" name="q" />
			</form>
		</div>
		<div class="seven columns show-on-desktops fr">
			<%--<div style="width: 728px; height: 91px; margin-top: 5px">
				
				<div style="width: 648px; height: 80px;POSITION: absolute;left: 0;top: 0;Z-INDEX: 2;">
				<embed height="80" width="648" flashvars="bannerWidth=648&bannerHeight=80&bannerSID=http://img.uu1001.cn/x3/2012-03-17/23-08/2012-03-17_7ff02fc97736a4e429eaa736adb41bdd_0.xml&bannerXML=&bannerLink=http%3A%2F%2F&dataSource=&bid=28787539&appSource=default" wmode="transparent" allowscriptaccess="always" quality="high" name="28787539" id="28787539" style="" src="http://img.uu1001.cn/bcv3.swf?v=20120313" type="application/x-shockwave-flash"/></embed>
				</div>
				<a href="http://i.jrj.com.cn" target="_blank"><img src="http://i0.jrjimg.cn/allyes/1.gif?local=1x1.gif" style="POSITION:absolute;left:0px;top:0px;Z-INDEX:3;width:648px;height:80px" border="0"></a>
				
			</div>
			--%>
		</div>
		</header>
	</div>