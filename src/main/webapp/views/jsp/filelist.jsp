<%--
  Created by IntelliJ IDEA.
  User: quanquan
  Date: 16-9-25
  Time: 下午5:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %><%--关闭EL的忽略解析--%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>file list</title>
    <meta charset="UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <style type="text/css">
        a:link {
            text-decoration: none;
        }

        a:visited {
            text-decoration: none;
        }

        a:hover {
            color: #999999;
            text-decoration: underline;
        }
        .wrapper {
            padding: 30px 0 0 30px;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <br/>
    <c:forEach items="${map}" var="v">
        <a href="<%=basePath%>download/file?filename=${v.key}">${v.key}</a><br/>
    </c:forEach>
</div>
</body>
</html>
