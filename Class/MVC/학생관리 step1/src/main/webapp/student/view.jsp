<%--
  Created by IntelliJ IDEA.
  User: kimhwajeong
  Date: 2023/04/05
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
  <head>
      <title>학생 조회</title>
      <meta charset="UTF-8"/>
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
    <h1>학생 조회</h1>
    <table>
      <tbody>
        <!-- todo view 구현 -->
        <tr>
          <th>아이디</th>
          <td>${student.id}</td>
        </tr>
        <tr>
          <th>이름</th>
          <td>${student.name}</td>
        </tr>
        <tr>
          <th>성별</th>
          <td>${student.gender}</td>
        </tr>
        <tr>
          <th>나이</th>
          <td>${student.age}</td>
        </tr>
        <tr>
          <th>등록일</th>
            <td><fmt:formatDate value="${student.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
        </tr>
      </tbody>
    </table>
    <p>
      <button onclick="window.open('/student/list')">리스트</button>

      <!-- todo ${update_link} 설정 c:url -->
      <c:url var="update_link" value="/student/update">
        <c:param name="id" value="${student.id}"/>
      </c:url>
      <button onclick="window.open('${update_link}')">수정</button>

      <!-- todo 삭제버튼 구현, method=post -->
      <form method="post" action="/student/delete">
        <input type="hidden" name="id" value="${student.id}"/>
        <button type="submit">삭제</button>
      </form>
    </p>
  </body>
</html>
