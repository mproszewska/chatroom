<!DOCTYPE HTML >
<html>
  <head>
    <style>
      body {
        font-family: courier;
      }
      input[type = button] {
        font-family: courier;
      }
    </style>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <script type="text/javascript" src="functions.js"></script>
    <title>The Chat Room</title>
    <link rel="SHORTCUT ICON" type="image/png" href="img/icon.png" />
  </head>
  <body>
    <div class="middle" id="result-data"	    
      <table>
        <tr>Login:</tr>
        <tr><input type="text" style="width:99%; margin-bottom:10px;" id="login" autocomplete="off"
          onkeypress="if(event.keyCode==13) document.getElementById('pw').focus();" ></tr>
	<tr>Password:</tr>
	<tr><input type="password" style="width:99%;" id="pw"  autocomplete="off"
          onkeypress="if(event.keyCode==13) document.getElementById('log-button').click();"></tr>
        <tr>
	  <td><input type="button" style="margin-top:10px;width:48%;" id='log-button' value="LOG-IN" onclick="log_in();"></td>
	  <td><input type="button" style=" margin-top:10px;width:48%;float:right; " value="SIGN-IN" onclick="sign_in();"></td>
	</tr> 
      </table>
    </div>

  </body>
</html>

