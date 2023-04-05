<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: kimhwajeong
  Date: 2023/04/04
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>공지사항</title>
    <style>
        table {
            width: 800px;
            border-collapse: collapse;
            border:1px #ccc solid;
            text-align: center;
        }
        table tr>td,th{
            padding:5px;
            border:1px #ccc solid;
        }
    </style>
</head>
<body>
    <h1>공지사항</h1>
    <table>
        <thread>
            <th>제목</th>
            <th>작성자</th>
            <th>조회수</th>
            <th>등록일</th>
        </thread>
        <tbody>
        <c:forEach var="item" items="${noticeList}">
            <tr>
                <td>${item.subject}</td>
                <td>${item.name}</td>
                <td>${item.counter}</td>
                <td>
                    <fmt:formatDate value="${item.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" />
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
