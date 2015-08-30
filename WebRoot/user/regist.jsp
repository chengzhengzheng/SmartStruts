<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<html>
	<head></head>
	<body style="font-size:30pt;">
		
				验证码:
				<img src="image" id="img1" />
				<a href="javascript:;"
					onclick="document.getElementById('img1').src='checkcode?' + Math.random();">换一个</a>
				<input type="text" name="number" />
	</body>
</html>
