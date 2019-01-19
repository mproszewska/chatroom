<html>
  <head>
    <title>Sign out</title>
      <link rel="stylesheet" type="text/css" href="styles.css">
      <link rel="SHORTCUT ICON" type="image/png" href="img/icon.png">
  </head>

  <body style="font-family:courier;font-size:16px;">
    <div class="middle-bis">
	<% session.invalidate();%>
	You are signed out.<br>
	<a href="http://localhost:8080/chatroom">Login</a> to continue.
      </div>
    </body>
</html>
