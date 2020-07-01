<!DOCTYPE html>
<html>
<head>
<title>Logout page</title>
<script type="text/javascript" >
  			 function preventBack(){
  				 window.history.forward();
  				 }
    		setTimeout("preventBack()", 0);
    		window.onunload=function(){null};
		</script>
		
		<script type="text/javascript">
	function showAlert() {
		alert("Are you sure you want to leave this page?");
		<%session.invalidate(); %>
		window.location.href = "http://localhost:8080/web-song-tracker/logout.html";
	}
	</script>
	
	<script type="text/javascript">
	function goBack() {
		window.location.href = "http://localhost:8080/web-song-tracker/search.html";
	}
	</script>
	
</head>
<body style="background: url('https://www.uscreen.tv/wp-content/uploads/2018/06/best-places-to-find-free-music-768x445.jpg'); background-repeat: no-repeat; background-size: 100%;">

<div align="center">
<h2 align="center" style="color: white">Are you sure you want to logout?</h2>
<button onclick="showAlert()">Yes</button>
<button onclick="goBack()">No</button>
</div>

</body>
</html>