package com.abc.project;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.entity.Model;

@WebServlet("/SongSearch")
public class SongSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String engine = request.getParameter("engine");
		String song = request.getParameter("song");

		//fetch the email from session
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		
		Model model = new Model();
		model.setEmail(email);
		model.insert(engine, song);
		
		if(engine.equals("Ganna"))
			response.sendRedirect("https://gaana.com/search/"+song);
		else if(engine.equals("Youtube"))
			response.sendRedirect("https://www.youtube.com/results?search_query="+song);
		else
			response.sendRedirect("https://www.google.com/search?q="+song);
		
	}

}
