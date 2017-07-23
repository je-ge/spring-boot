<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提交xml</title>
</head>
<body>
  <form action="/user" method="post" enctype="text/xml">
    <input type="text" name="user" value="<user><id>1</id><Content>JE-GE</Content></user>">
    <input type="submit">
  </form>
</body>
</html>