<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="SITE_URL" value="${pageContext.request.contextPath}"/>
<c:set var="loggedInUser" value="${loggedIn}"/>
<c:set var="unreadMsgsCount" value="${unreadMsgsNumber}"/>
<%--<c:set var="unreadMsgsCount" value="0" scope="application"/>--%>
<%--<c:forEach var="m" items="${allMessages}">
    <c:if test="${m.receiver.userName==loggedInUser.userName && !m.readStatus}">
        <c:set var="unreadMsgsCount" value="${unreadMsgsCount+1}" scope="application"/>
    </c:if>
</c:forEach>--%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">
            a {text-decoration: none !important;}
            a:hover{color: goldenrod;}
            .btn-link{text-decoration: none !important;}
            .btn-link:hover{color: goldenrod;}
            .btn-link:focus,
            .btn-link:active:focus{outline:none;}
            .btn-link:focus{text-decoration:none;}
        </style>
        <title>Zeppelin Event Planners</title>
    </head>
    <body style="background:black">
        <div class="container" id="top-header" style="background: palegoldenrod;">
            <table style="width: 100%; height: 40px; vertical-align: middle">
                <tr>
                    <td>
                        <a href="${SITE_URL}/home"><span class="glyphicon glyphicon-home"></span> Home</a>
                    </td>
                    <td align="right">
                        <c:if test="${not empty loggedIn}">
                            Logged in as: ${loggedInUser.userName} |  
                            <a href="${SITE_URL}/inbox?all=1" style="color:green" title="Inbox">
                                <span class="glyphicon glyphicon-envelope"></span>
                                <c:if test="${unreadMsgsCount>0}">
                                    <font color="red"><sup><b>${unreadMsgsCount}</b></sup></font>
                                </c:if>
                            </a> | 
                            <a href="${SITE_URL}/logout" style="color:red">
                                <span class="glyphicon glyphicon-off"></span> Log Out
                            </a>
                        </c:if>
                        <c:if test="${empty loggedIn}">
                            <a href="${SITE_URL}/signup">
                                <span class="glyphicon glyphicon-plus"></span> Sign Up
                            </a> | 
                            <a href="${SITE_URL}/login">
                                <span class="glyphicon glyphicon-user"></span> Log In
                            </a>
                        </c:if>
                    </td>
                </tr>
            </table>
        </div>
        <div class="container" style="background:white">