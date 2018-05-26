<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="keywords" content="${keywords}"/>
    <meta name="description" content="${description}"/>
    <link href="${icon}" rel="shortcut icon" type="image/x-icon"/>
    <link href="${icon}" rel="icon" type="image/x-icon"/>
    <link href="${icon}" rel="shortcut" type="image/x-icon"/>
    <link href="<%=basePath %>resources/framework/bootstrap-3.0.0/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <!-- base-min.css,admin.css应该发在bootstrap之后,覆盖部分bootstrap样式 -->
    <link href="<%=basePath %>resources/css/base.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath %>resources/css/crapApi.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath %>resources/css/setting.css" rel="stylesheet" type="text/css"/>
    <title>${title}</title>
</head>
<body class="BGFFF">
<!-- top navbar -->
<nav class="navbar navbar-inverse no-radius FOOTER_COLOR mb0 z10">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed"
                    data-toggle="collapse" data-target="#navbar" aria-expanded="false"
                    aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>

            <a class="navbar-brand p0 pt10 ml10" href="${domain}">
                <img class="h30" src="${logo}"/>
            </a>
        </div>
        <div id="navbar" class="navbar-collapse collapse p0">
            <ul class="nav navbar-nav navbar-right ml20">
                <li><a href='http://api.crap.cn/static/help/help-articleList--1.html' target="_blank" class="menu-a">帮助文档
                    <i class="iconfont adorn-color f14">&#xe63e;</i></a>
                </li>
                <li>
                    <a href='https://gitee.com/CrapApi/ApiDebug'
                       target="_blank" class="menu-a">插件下载 <i class="iconfont adorn-color f14">&#xe624;</i></a>
                </li>
                <c:if test="${login}">
                    <li><a class="cursor" onclick="loginOut()"><i class="iconfont f16 mt-5 adorn-color">&#xe609;</i>&nbsp;&nbsp;注销</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>

<div class="login-bg h500 mb20 w p0">
    <div class="trans">
        <div class="container">
            <div class="row p0 m0">
                <div class="col-xs-12 col-md-9 mt100">
                    <div class="CFFF f30 mt130">开源API接口管理、协同、调试系统</div>
                    <div class="CFFF f16 mt20 mb20">阿里技术历时2年打造的专业开源、免费接口管理系统：PDF一键生成、在线调试、项目协作...</div>

                    <c:if test="${login}">
                        <a class="btn btn-adorn btn-sm r20 w150 f14" href="admin.do" target="_self">进入项目管理</a>
                        <a class="btn btn-adorn btn-sm r20 w150 f14 ml10" href="index.do#/project/list/true/NULL"
                           target="_self">查看项目</a>
                    </c:if>
                    <c:if test="${login == false}">
                        <a class="btn btn-adorn btn-sm r20 w150 f14" href="loginOrRegister.do#/register" target="_self">快速注册<i
                                class="iconfont f18 pl10">&#xe604;</i></a>
                        <a class="btn btn-adorn btn-sm r20 w150 f14 ml10" href="loginOrRegister.do#/login"
                           target="_self">登陆<i class="iconfont f18 pl10">&#xe601;</i></a>
                    </c:if>
                </div>
                <div class="hidden-sm hidden-xs col-md-3  p50 shadow h300 mt100 r10 dashboard f12">
                    <div class="adorn-bl-3 f14 pl10 mb20">数据大盘「Dashboard」</div>
                    注册用户：<span class="adorn-color">${totalUser}</span><br>
                    管理项目数：<span class="adorn-color">${totalProject}</span><br>
                    接口总数：<span class="adorn-color">${totalInterface}</span><br>
                    发布文章数：<span class="adorn-color">${totalArticle}</span><br>
                    码云统计：<br>
                    <div class="mt10">
                        <a href='https://gitee.com/CrapApi/CrapApi/stargazers'><img
                                src='https://gitee.com/CrapApi/CrapApi/badge/star.svg?theme=dark' alt='star'></img></a>
                        <a href='https://gitee.com/CrapApi/CrapApi/members'><img src='https://gitee.com/CrapApi/CrapApi/badge/fork.svg?theme=dark' alt='fork'></img></a>
                    </div>
                    <a class="btn btn-main btn-sm w f14 mt20 r20" href="http://v.youku.com/v_show/id_XMzU4NjQwODIzNg==.html" target="_blank">
                        视频-用户版<i class="iconfont f18">&#xe634;</i></a>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- End: top-->

<div class="container p0 mt10">
    <div class="row p0 m0">
        <div class="col-sm-3 col-xs-12 b1 pb20">
            <c:forEach items="${menuList}" var="menuDto" varStatus="id">
                <c:if test="${menuDto.menu.type=='FRONT'}">
                    <div class="cb dashed-b p3 pl10 pr10 mr10 f16 fw600 mt10 mb10">
                        <span class="adorn-color fn">${menuDto.menu.iconRemark}</span> ${menuDto.menu.menuName}
                    </div>
                    <c:forEach items="${menuDto.subMenu}" var="subMenu" varStatus="id">
                        <a class="dis fl b1 p3 pl10 pr10 r12 mr10 f12 fn no_unl hover-a C999 mb5" href="${subMenu.menuUrl}">${subMenu.menuName}</a>
                    </c:forEach>
                </c:if>
            </c:forEach>

        </div>
        <div class="col-sm-9 col-xs-12">
            <div class="cb dashed-b p3 pl10 pr10 mr10 f16 fw600 mt10 mb10 adorn-bl-3">
                推荐文章
                <div class="fr f12"><a class="adorn-color fn" href="index.do#NULL/article/list/NULL/ARTICLE/NULL/NULL/2">More...</a></div>
            </div>
            <c:forEach items="${articleList}" var="article" varStatus="id">
                <div class="dashed-b">
                    <a href="index.do#/${article.projectId}/article/detail/${article.moduleId}/${article.type}/${article.id}"
                       class="p10 pl0 f14 fw600 dis w C000 no_unl">${article.name}</a>
                    <div class="f12 C999">${article.brief}</div>
                    <div class="tr C999 f12 p10">点击量:${article.click}次 <span
                            class="C999 pl20">${article.createTimeStr}</span></div>
                </div>
            </c:forEach>
        </div>


        <div class="cb f30 w tc dashed-b fw600 pt20">推荐项目
            <a class="adorn-color f12 fn" href="index.do#/project/list/false/NULL">More...</a>
        </div>
        <c:forEach items="${projectList}" var="item" varStatus="id">
            <div class="col-sm-6 col-md-4 col-lg-3 m0 p0">
                <div class="b1 tl r3 h220 m15 p15">
                    <div>
                        <a class="fl" href="project.do#/${item.id}/module/list" target="_blank">
                            <img class="h70 w70 r50P" src="${item.cover}"/>
                        </a>
                        <div class="lh26 fl mt20 ml10">
                            <a class="f12 text-primary mr5 cursor" href="project.do#/${item.id}/error/list"
                               target="_blank">
                                <i class="iconfont f12">&#xe608; 错误码</i>
                            </a>
                            <br/>
                            <a class="f12 text-primary mr5 cursor mt10" href="project.do#/${item.id}/module/list"
                               target="_blank">
                                <i class="iconfont f12">&#xe613; 模块列表</i>
                            </a>
                        </div>
                    </div>
                    <div class="cb"></div>
                    <div class="h30 of-h f14 C000 pt10">
                        <a href="project.do#/${item.id}/module/list" target="_blank"
                           class="adorn-color">${item.name}</a>
                    </div>
                    <div class="h80 of-h C555 pt10 pb5">
                            ${item.remark}
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>


<!-- footer navbar -->
<div class="p0 m0 mt30 def-bg w FOOTER_BG_COLOR FOOTER_COLOR">
    <div class="container p0">
        <div class="row p0 m0">
            <div class="col-xs-12 f12 p0 mb5 tl mt50 mb50">
                ©crap.cn&nbsp;版本号 [V8.0.0]
                <ul class="dis-in-tab">
                    <li class="dis-in-tab mr20">
                        <a target="_blank" href="http://api.crap.cn/static/help/help-articleList--1.html">帮助文档</a>
                    </li>
                    <li class="dis-in-tab mr20">
                        <a target="_blank" href="https://github.com/EhsanTang/CrapApi">源码:GitHub</a>
                    </li>
                    <li class="dis-in-tab mr20">
                        <a target="_blank" href="https://git.oschina.net/CrapApi/CrapApi">源码:码云</a>
                    </li>
                    <c:forEach items="${menuList}" var="menuDto" varStatus="id">
                        <c:if test="${menuDto.menu.type=='BOTTOM'}">
                            <a target="_blank" class="mr20" href="${menuDto.menu.menuUrl}">${menuDto.menu.menuName}</a>
                        </c:if>
                    </c:forEach>
                </ul>
                <div class="mt20">
                    友情链接：
                    <ul class="dis-in-tab p0">
                        <li class="dis-in-tab mr20"><a target="_blank" href="http://api.crap.cn">CrapApi官网</a> <span
                                class="bg_line"></span></li>
                        <c:forEach items="${menuList}" var="menuDto" varStatus="id">
                            <c:if test="${menuDto.menu.type=='FRIEND'}">
                                <a target="_blank" class="mr20" href="${menuDto.menu.menuUrl}">${menuDto.menu.menuName}</a>
                            </c:if>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="resources/framework/jquery-1.9.1.min.js"></script>
<script src="resources/js/core.js?v=200"></script>
<script src="resources/js/crapApi.js?v=200"></script>
</body>
</html>
