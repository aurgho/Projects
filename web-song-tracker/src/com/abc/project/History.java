package com.abc.project;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.entity.Model;

@WebServlet("/History")
public class History extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String engine = request.getParameter("engine");
		String history = request.getParameter("history");
		
		//fetch the email from the session
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		
		Model model = new Model();
		model.setEmail(email);
		
		if(history.equals("history"))
		{
			model.engineHistory(engine);
			List<String> song_list = model.getSong_list();
			session.setAttribute("song_list", song_list);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/engineHistory.jsp");
			dispatcher.forward(request, response);
		}
		else
		{
			model.deleteHistory(engine);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/search.html");
			dispatcher.forward(request, response);
		}
			
		
		
		
	}

}
