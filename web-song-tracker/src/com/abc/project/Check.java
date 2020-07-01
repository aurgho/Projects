package com.abc.project;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.entity.Model;

@WebServlet("/Check")
public class Check extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		
		Model model = new Model();
		model.setEmail(email);
		model.setUserName(username);
		
		if(model.check()) {
			
			HttpSession session = request.getSession(true);
			session.setAttribute("email", email);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/reset.html");
			dispatcher.forward(request, response);
		}
		else
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/failedCheck.html");
			dispatcher.forward(request, response);
		}
			
		
		
		
	}

}
