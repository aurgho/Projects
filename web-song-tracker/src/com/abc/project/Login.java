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

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String pwd = request.getParameter("password");
		
		//put the email in session
		HttpSession session = request.getSession(true);
		session.setAttribute("email", email);
		
		Model model = new Model();
		model.setEmail(email);
		model.setPwd(pwd);
		
		if(model.login()) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/search.html");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/failedLogin.html");
			dispatcher.forward(request, response);
		}
		
	}

}
