<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.yuanlong.moiverss.channel.constant.DyZyConstant" %>
	<div class="header">
  <div class="header-brand w960 marM">
    <h1 class="tac"> <a href="default.htm" class="koudai-logo png ft30"  hidefocus="hidefocus">高清电影订阅</a> </h1>
    <div class="" style="position: absolute;right: 0;top:0;">
    </div>
    <div class="right-corner">
      <p class="float-left"> </p>
      <p class="float-left"> </p>
    </div>
  </div>
  <div class="header-menu">
    <div class="header-menu-wrapper w960 marM clearfix">
      <ul class="header-menu-content float-left ml15">
        <li> <a id="menu-home" class="main-menu-item" href="/v2/index"  hidefocus="hidefocus"> 首页 <span class="main-menu-sub"></span> </a> </li>
        <li><span class="main-menu-seperate">|</span></li>
        <%for(String key:DyZyConstant.CLASS_LIST){%>
        <li> <a id="menu-girl" class="main-menu-item" href="/category?cn=<%=key %>" hidefocus="hidefocus"><%=key%> <span class="main-menu-sub"></span> </a> </li>
        <li><span class="main-menu-seperate">|</span></li>
        <%}%>
        <li>
        </li>
      </ul>
      <div id="J_search" class="search-form search-form-default float-right">
        <input name="searchWordCn" id="J_searchWordCn" class="search-word-cn" type="text" value="搜电影" onkeypress="searchEnterPress(event);">
        <form id="J_searchForm" action="/search" method="get" target="_blank">
          <input name="q" value="" id="J_searchWord" type="hidden" />
          <input value="搜索" id="J_searchBtn" class="search-btn" type="submit" hidefocus="hidefocus" />
        </form>
      </div>
    </div>
  </div>
</div>
<div id="goTop" class="goTop">
			<a class="pointer png">返回顶部</a>
</div>