<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/numbertld" prefix="number"%>

<!DOCTYPE html>
<html>
<head>
<title>Yahoo!!</title>
</head>
<body>
	<p>
		<font color="red">${errorMessage}</font>
	</p>
	
	<form action="/welcome" method="POST">
	 Select User: <number:process /><br></br>
	  Select Sort type: <number:sort />      Select Sort order: <number:aOrD/><br></br> 
		<input type="submit" name="submituser" value="Show User Process" /> 
		<br><br>
		<input
			type="submit" name="submitsystem" value="Show System Process" />
			<br>
		
	</form>
</body>
</html>