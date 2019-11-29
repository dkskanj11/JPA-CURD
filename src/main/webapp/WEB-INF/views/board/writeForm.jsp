<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
</head>
<body>
	<form action="/board/write" method="post">
		<table>
			<tr>
				<th>Title</th>
				<td><input type="text" name="title" /></td>
			</tr>

			<tr>
				<th>Content</th>
				<td>
				<textarea rows="3" cols="10" name="content"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="완료" /></td>
			</tr>
		</table>
	</form>
</body>
</html>