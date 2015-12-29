<html>

<body>
	<h1>HELLO USER</h1>

	<h2>${user.userName}</h2>
	<h2>${userAddress.city}</h2>
	
    <form action="userreg" method="post">
    	First name:<br>
		<input type="text" name="userName">
		<br>
		Last name:<br>
		<input type="text" name="userSurName">
		<br>
		Logine:<br>
		<input type="text" name="userLogin">
		<br>
		Password:<br>
		<input type="text" name="userPassword">
		
		
		
		<br><br><br><br><br><br><br>
		
		
		street:<br>
		<input type="text" name="street">
		<br>
		City:<br>
		<input type="text" name="city">
		<br>
		State:<br>
		<input type="text" name="state">
		<br>
		Zip code:<br>
		<input type="text" name="zipcode" value="81500">
		<br>
		
		<br><br>
		<input type="submit" value="Submit">
	</form>
	
	
</body>
</html>