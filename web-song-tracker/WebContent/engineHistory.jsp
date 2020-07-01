<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<title>History of Songs Searched</title>
</head>

<body
	style="background: url('https://www.uscreen.tv/wp-content/uploads/2018/06/best-places-to-find-free-music-768x445.jpg'); background-repeat: no-repeat; background-size: 100%;">

	<br>
	<h1 align="center" style="color: white">List of Songs Searched</h1>
	<br>
	<div align="center" style="color: white">
		<table>
			<tr>
				<td>
					<ol>
						<%
							List<String> song_list = (ArrayList<String>) session.getAttribute("song_list");

							for (String tempSong : song_list) {
								out.println("<h2><li>" + tempSong + "</li></h2>");
							}
						%>
					</ol>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>



