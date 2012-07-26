<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.yuanlong.moiverss.vo.*,com.yuanlong.moiverss.utils.*,java.util.*" %>
<%
Map<String,List<MoiveDetail>> tmap=(Map<String,List<MoiveDetail>>)request.getAttribute("tmap");
Map<String,List<MoiveDetail>> smap=(Map<String,List<MoiveDetail>>)request.getAttribute("smap");
Date time=(Date)request.getAttribute("time");
%>
<!doctype html>
<html>
<head>
<meta name="keywords"
	content="电影订阅,新版订阅,免费订阅,qvod,快播,快播订阅,moiverss,dyrss,高清电影,高清电影订阅中心,高清快播" />
<meta name="description" content="免费高清电影订阅网站，致力于提供优质的高清电影快播资源播放地址。" />
<title>免费高清电影订阅中心-新版</title>
<%@ include file="/v2/inc/common.inc"%>
<link rel="stylesheet" type="text/css" href="/v2/css/lanrentuku.css" />
<script type="text/javascript" src="/v2/js/lanrentuku.js"></script>
</head>
<body id="cat-">
	<div class="wrapper">
		<%@ include file="/v2/inc/header.inc"%>
		<div class="main">
			<div class="main-body w960 marM">
				<div class="home-client">
<DIV id=imgPlay>
<UL class=imgs id=actor>
  <LI><A href="http://www.lanrentuku.com/" target=_blank><IMG title=板长寿司2010罗志祥舞法舞天北京演唱会 src="images/01.jpg"></A><DIV class=btn><A title=立即购买 href="http://www.lanrentuku.com/" target=_blank>立即购买</A></DIV></LI>
  <LI><A href="http://www.lanrentuku.com/" target=_blank><IMG title=张学友2011巡回演唱会北京站 src="images/02.jpg"></A><DIV class=btn><A title=立即购买 href="http://www.lanrentuku.com/" target=_blank>立即购买</A></DIV></LI>
  <LI><A href="http://www.lanrentuku.com/" target=_blank><IMG title=爱无止境Air Supply空气补给站北京演唱会 src="images/03.jpg"></A><DIV class=btn><A title=立即购买 href="http://www.lanrentuku.com/" target=_blank>立即购买</A></DIV></LI>
  <LI><A href="http://www.lanrentuku.com/" target=_blank><IMG title=2010迈克学摇滚中国巡演北京演唱会  src="images/04.jpg"></A><DIV class=btn><A title=立即购买 href="http://www.lanrentuku.com/" target=_blank>立即购买</A></DIV></LI>
  <LI><A href="http://www.lanrentuku.com/" target=_blank><IMG title=2010张杰北京演唱会 src="images/05.jpg"></A><DIV class=btn><A title=立即购买 href="http://www.lanrentuku.com/" target=_blank>立即购买</A></DIV></LI>
  <LI><A href="http://www.lanrentuku.com/" target=_blank><IMG title=海上良宵”蔡琴2010北京演唱会 src="images/06.jpg"></A><DIV class=btn><A title=立即购买 href="http://www.lanrentuku.com/" target=_blank>立即购买</A></DIV></LI>
</UL>
<DIV class=num>
<P class=lc></P>
<P class=mc></P>
<P class=rc></P></DIV>
<DIV class=num id=numInner></DIV>
<DIV class=prev>上一张</DIV>
<DIV class=next>下一张</DIV></DIV>
				
				
				</div>
				<!-- 最新更新 -->
				<div class="home-daily-hots">
					<h3 class="home-daily-hots-title">
						<span class="home-daily-hots-word">最新更新</span> <span
							class="home-daily-hots-day"><%=DateUtil.format(time, "MM月dd日") %></span>
					</h3>
					<div class="home-daily-hots-content">
						<table class="box-shadow-corner-home">
							<tr class="bs-header">
								<td class="bs-left"></td>
								<td class="bs-middle"></td>
								<td class="bs-right"></td>
							</tr>
							<tr class="bs-body">
								<td class="bs-left"></td>
								<td class="bs-main">
									<div class="home-daily-hot-list">
										<ul class="home-daily-hot-list-wrapper clearfix">
										<% Set<String> keys=tmap.keySet();
											Iterator<String> it=keys.iterator();
											while(it.hasNext()){
												String key=it.next();
												List<MoiveDetail> list=tmap.get(key);
										%>
											<li><div class="h-daily-hots-item">
													<p class="h-daily-hots-item-name"><%=key %></p>
													<div class="h-daily-hots-item-wrapper">
														<%for(int i=0;i<list.size();i++){ 
															MoiveDetail md=list.get(i);
														%>
														<div
															class="<%=i==0?"J_HDailyHotFirst h-daily-hot-first":"J_HDailyHot" %> h-daily-hot">
															<p class="picture-picture <%=i==0?"h252":"h120"%>">
																<a class="picture-wrapper" target="_blank"
																	href="/detail?id=<%=md.getId() %>" title="<%=md.getName() %>"> <img
																	alt="<%=md.getName() %>"
																	src="<%=md.getPic() %>">
																	<span
																	class="h-daily-hot-info"><%=md.getName() %></span>
																</a>
															</p>
														</div>
														<%} %>
													</div>
												</div></li>
												<%
												} %>
										</ul>
									</div>
								</td>
								<td class="bs-right"></td>
							</tr>
							<tr class="bs-footer">
								<td class="bs-left"></td>
								<td class="bs-middle"></td>
								<td class="bs-right"></td>
							</tr>
						</table>
					</div>
				</div>
				<!-- 最新更新结束 -->
				<!-- 分类 -->
				<%Set<String> skeys=smap.keySet();
				Iterator<String> sit=skeys.iterator();
				int j=0;
				while(sit.hasNext()){
					j++;
					String key=sit.next();
					List<MoiveDetail> list=smap.get(key);
				%>
				<div  class="home-channel">
					<table class="box-shadow-corner-home">
						<tr class="bs-header">
							<td class="bs-left"></td>
							<td class="bs-middle"></td>
							<td class="bs-right"></td>
						</tr>
						<tr class="bs-body">
							<td class="bs-left"></td>
							<td class="bs-main"><div class="home-channel-content">
									<h3 class="home-channel-name home-channel-name-<%=j%>">
										<a href="/category?cn=<%=key %>" target="_blank"><%=key %></a>
									</h3>
									<div class="home-channel-wrapper">
										<div class="J_homeChannelItems home-channel-itemlist">
											<ul class="clearfix">
												<%for(int i=0;i<6;i++){
													MoiveDetail md=list.get(i);
													%>
												<li><div class="home-channel-item">
														<p class="picture-picture h208">
															<a class="picture-wrapper" href="/detail?id=<%=md.getId() %>"
																target="_blank" title="<%=md.getName() %>"> <img
																alt="<%=md.getName() %>" 
																src="<%=md.getPic()%>"> <span
																class="home-channel-item-name"><%=md.getName() %></span>
															</a>
														</p>
													</div></li>
													<%} %>
											</ul>
										</div>
										<div class="home-channel-keywordlist">
											<ul class="clearfix">
											<%for(int i=6;i<list.size();i=i+2){
												MoiveDetail md1=list.get(i);
												MoiveDetail md2=list.get(i+1);
												%>
												<li
													class="h-channel-keyword-item <%=i==6?" h-channel-keyword-item-first":(i==list.size()-2?"h-channel-keyword-item-last":"") %>">
													<div class="h-channel-keyword-item-wrapper">
														<div class="h-channel-keyword-item-lines">
															<p class="h-channel-keyword-item-line">
																<a class="h-channel-keyword-item-word <%=i%3%2==1?"hot":"" %>"
																	href="/detail?id=<%=md1.getId() %>" target="_blank" title="<%=md1.getName()%>"><%=StringUtils.toLength(md1.getName(),20) %></a> 
															</p>
															<p class="h-channel-keyword-item-line">
																<a class="h-channel-keyword-item-word <%=(i+1)%3%2==1?"hot":"" %>"
																	href="/detail?id=<%=md2.getId() %>" target="_blank" title="<%=md2.getName()%>"><%=StringUtils.toLength(md2.getName(),20) %></a>
															</p>
														</div>
													</div>
												</li>
												<%} %>
												<li class="h-channel-keyword-all"><a
													class="h-channel-keyword-all-content" href="/category?cn=<%=key %>"
													target="_blank">全部&raquo;</a></li>
											</ul>
										</div>
									</div>
								</div></td>
							<td class="bs-right"></td>
						</tr>
						<tr class="bs-footer">
							<td class="bs-left"></td>
							<td class="bs-middle"></td>
							<td class="bs-right"></td>
						</tr>
					</table>
				</div>
				<%}%>
				<!-- 分类结束 -->
			</div>
		</div>
		<!-- main end -->
		<!-- foot -->
				<%@ include file="/v2/inc/footer.inc"%>
		<!-- foot end -->
	</div>


</body>
</html>