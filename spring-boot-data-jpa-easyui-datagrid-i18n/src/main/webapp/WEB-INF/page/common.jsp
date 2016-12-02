<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- c标签：定义项目的路径 -->
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- easyui默认主题样式 -->
<link rel="stylesheet" type="text/css" href="${ctx}/static/easyui/themes/default/easyui.css">
<!-- easyui图标样式-->
<link rel="stylesheet" type="text/css" href="${ctx}/static/easyui/themes/icon.css">
<!-- easyui颜色样式 -->
<link rel="stylesheet" type="text/css" href="${ctx}/static/easyui/themes/color.css">
<!-- 先引入jQuery核心的js -->
<script type="text/javascript" src="${ctx}/static/easyui/jquery.min.js"></script>
<!-- 在引入easyui的核心的js-->
<script type="text/javascript" src="${ctx}/static/easyui/jquery.easyui.min.js"></script>
<!-- 国际化的js-->
<c:if test="${fn:contains(pageContext.request.locale, 'en')}">
<script type="text/javascript" src="${ctx}/static/easyui/locale/easyui-lang-en.js"></script>
</c:if>
<c:if test="${fn:contains(pageContext.request.locale, 'zh')}">
<script type="text/javascript" src="${ctx}/static/easyui/locale/easyui-lang-zh_CN.js"></script>
</c:if>