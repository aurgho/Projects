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

@WebServlet("/Reset")
public class Reset extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pwd = request.getParameter("password");
		String cpwd = request.getParameter("cpassword");
		
		if(pwd.equals(cpwd))
		{
			HttpSession session = request.getSession();
			String email = (String) session.getAttribute("email");
			
			Model model = new Model();
			model.setEmail(email);
			model.setPwd(pwd);
			
			if(model.reset()) 
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("/successReset.html");
				dispatcher.forward(request, response);
			}
			else
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("/failedReset.html");
				dispatcher.forward(request, response);
			}
		}
		else
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/resetError.html");
			dispatcher.forward(request, response);
		}
		
	}

}
