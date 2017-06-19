<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>

<html lang="pt-BR" id="ng-app">

<head>
<title>Login Page</title>
</head>
<body>
	<div>
		<form action="<c:url value='/validateLogin' />" method="POST">
			<div style="margin-top: 1%;">
				<input type="submit" value="Login" />
			</div>
		</form>
	</div>
</body>
</html>