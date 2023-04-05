<%--
  Created by IntelliJ IDEA.
  User: kimhwajeong
  Date: 2023/04/05
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>학생 리스트</title>
        <style>
            table {
                border: 1px solid;
            }

            table tr>td,th{
                padding:5px;
                border:1px #ccc solid;
                text-align: center;
            }
        </style>
    </head>

    <body>
        <h1>학생 리스트</h1>
        <p><a href="/student/register">학생 등록</a> </p>
        <table>
            <thread>
                <tr>
                    <th>아이디</th>
                    <th>이름</th>
                    <th>성별</th>
                    <th>나이</th>
                    <th>cmd</th>
                </tr>
            </thread>
            <tbody>
            <!-- todo list 구현하기 c: foreach -->
            <c:forEach var="item" items="${studentList}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td>${item.gender}</td>
                    <td>${item.age}</td>
                    <td>
                        <c:url var="view_link" value="/student/view" scope="request">
                            <c:param name="id" value="${item.id}" />
                        </c:url>
                        <a href="${view_link}">조회</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </body>
</html>
