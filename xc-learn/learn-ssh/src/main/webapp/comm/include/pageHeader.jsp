<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- TDK设置，用于SEO --%>
<c:set var="ptitle" value="时间的灰烬"></c:set>
<c:set var="pdescription" value="时间的灰烬--作者：miraclelord@163.com"></c:set>
<c:set var="pkeywords" value="时间的灰烬,java,google开源项目"></c:set>

<c:if test="${not empty param.ptitle }">
	<c:set var="ptitle" value="${param.ptitle }"></c:set>
</c:if>
<c:if test="${not empty param.pdescription }">
	<c:set var="pdescription" value="${param.pdescription }"></c:set>
</c:if>
<c:if test="${not empty param.pkeywords }">
	<c:set var="pkeywords" value="${param.pkeywords }"></c:set>
</c:if>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>${ptitle }</title>
    <meta name="description" content="${pdescription }">
    <meta name="keywords" content="${pkeywords }">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="author" content="miraclelord@163.com">
    
    <link type="image/x-icon" href="${imgServer }/x-icon.png" rel="shortcut icon">
    <link href="${cssServer }/comm.css" rel="stylesheet">
    <link href="${cssServer }/bootstrap.min.css" rel="stylesheet">
    <link href="${cssServer }/bootstrap-responsive.min.css" rel="stylesheet">
    
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <script src="${jsServer }/jquery-1.9.1.min.js"></script>
    <script src="${jsServer }/bootstrap.min.js"></script>
    
  </head>

  <body>
  	<div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          
          <a class="brand" href="#">时间的灰烬</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active"><a href="${dynSite }">首页</a></li>
              <li><a href="#">新闻中心</a></li>
              <li><a href="#">电子商务</a></li>
              <li><a href="${dynSite }/account/account_listAccount">记帐功能</a></li>
              <li><a href="#">论坛</a></li>               
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">个人作品<b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="#">新闻中心</a></li>
                  <li><a href="#">技术论坛</a></li>
                  <li><a href="#">下载中心</a></li>
                  <li class="divider"></li>
                  <li class="nav-header">游戏中心</li>
                  <li><a href="#">电子商务</a></li>
                  <li><a href="${dynSite }/account/account_listAccount">记帐功能</a></li>
                </ul>
              </li>
              <li><a href="#about">关于我</a></li>
              <li><a href="#contact">联系我</a></li>
            </ul>
            <form class="navbar-form pull-right">
              <input class="span2" type="text" placeholder="用户名">
              <input class="span2" type="password" placeholder="密 码">
              <button type="submit" class="btn">登录</button>
            </form>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>