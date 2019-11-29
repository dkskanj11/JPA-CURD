<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정하기</title>
</head>
<body>
	<form:form action="/board/update" method="put">
	<input type="hidden" name="id" value="${board.id}" />
	<input type="hidden" name="createDate" value="${board.createDate}" />
		<table>
			<tr>
				<th>Title</th>
				<td><input type="text" name="title" value="${board.title}" /></td>
			</tr>

			<tr>
				<th>Content</th>
				<td><textarea rows="3" cols="10" name="content">${board.content}</textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="수정완료" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>