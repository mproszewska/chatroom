
<%if(session.getAttribute("login")==null)
  response.sendRedirect("/chatroom");%>

<html>
  <%String login=(String)session.getAttribute("login"); %>
  <head>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <script type="text/javascript" src="functions.js"></script>
    <title>The Chat Room</title>
    <link rel="SHORTCUT ICON" type="image/png" href="img/icon.png">

    <script>
      setInterval(reload, 1000);
    </script>

  </head>

  <body style="font-family:courier;" >
    <p id="result"></p>
    <p style="float:left; margin-left:26%;"> Welcome, <%=login%>. </p>
    <p style="float:right; margin-right:24%;"><a href="logout.jsp" >Logout</a></p>

    <div id="list" class="always-middle" style="overflow-y:auto;">
      <div id="content"></div>	
    </div>

    <input style="display: none; " type="text" id="u" value="<%=login%>">	
    <textarea id='h' class="textbox" style="font-family:courier;font-size:14;"
		     onkeydown = "if (event.keyCode == 13){ document.getElementById('btn').click(); return false}" ></textarea>
    <input type="button"  class="btn" style="background-image:url(img/send.png);background-size:100% 100%;" id="btn" 
	    onclick="send_msg(); olist = document.getElementById('list'); olist.scrollTop = olist.scrollHeight;" />	
  </body>
</html>
