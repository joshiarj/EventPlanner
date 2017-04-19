<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@page session="false" %>--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="SITE_URL" value="${pageContext.request.contextPath}"/>
<c:set var="loggedInUser" value="${loggedIn}"/>
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
<!--        <style type="text/css">
            a {text-decoration: none;}
        </style>-->
        <title>Zeppelin Event Planners</title>
    </head>
    <body>
        <div class="container">
            <div id="top-header">
                <!--style="background:lightcyan; position:fixed; width: 88%;">-->
                <table style="width: 100%">
                    <tr>
                        <td>
                            <a href="${SITE_URL}/home"><span class="glyphicon glyphicon-home"></span> Home</a>
                        </td>
                        <td align="right">
                            <c:if test="${not empty loggedIn}">
                                Logged in as: ${loggedInUser.userName} |  
                                <a href="${SITE_URL}/logout" style="color:red">
                                    <span class="glyphicon glyphicon-log-out"></span> Log Out
                                </a>
                            </c:if>
                            <c:if test="${empty loggedIn}">
                                <a href="${SITE_URL}/signup">
                                    <span class="glyphicon glyphicon-user"></span> Sign Up
                                </a> | 
                                <a href="${SITE_URL}/login">
                                    <span class="glyphicon glyphicon-log-in"></span> Log In
                                </a>
                            </c:if>
                        </td>
                    </tr>
                </table>
            </div>