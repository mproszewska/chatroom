function log_in() {
    var login = document.getElementById('login');
    var pw = document.getElementById('pw');
    if (login.value == '' || pw.value == '') {
        alert('Please enter your login and password.');
        return false;
    }

    var xmlhttp= new XMLHttpRequest();
    xmlhttp.open("POST", "chat?login="+login.value+"&pw="+pw.value, true);

    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	    if (xmlhttp.responseText.indexOf('OK') != -1) {
	    	window.location.replace("room.jsp");    
            } else{
                alert('Incorrect login or password');	
		return false;
	    }
        }
    }
    xmlhttp.send(null);
}

function sign_in() {
    var login = document.getElementById('login');
    var pw = document.getElementById('pw');
    if (login.value == '' || pw.value == '') {
        alert('Please enter login and password.');
        return false;
    }

    var xmlhttp= new XMLHttpRequest();
    xmlhttp.open("POST", "signin?login="+login.value+"&pw="+pw.value, true);

    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            if (xmlhttp.responseText.indexOf('OK') != -1) {
            	confirm('Your account has been created.')
            } else {
                alert('Login in unavailable.');	
	    }
	    return false;
        }
    }
    xmlhttp.send(null);
}

 
function send_msg() {
    var login = document.getElementById("u").value;
    var msg = document.getElementById('h').value;
    olist = document.getElementById('list');
    if(msg.length==0) return; 
    ocontent = document.getElementById('content');
    document.getElementById('h').value = '';

    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", "chatmsg?login=" + login + "&msg=" + msg, true);
    xmlhttp.send(null);
}
function reload()	
{
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", "reloadchat", true);
    xmlhttp.onreadystatechange = function(){
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200){
	    document.getElementById("content").style.marginLeft = "10px";
            document.getElementById("content").innerHTML = xmlhttp.responseText;
	    var list = document.getElementById('list');
	    list.scrollTop = list.scrollHeight;
	}
    }
    xmlhttp.send(null);
}

