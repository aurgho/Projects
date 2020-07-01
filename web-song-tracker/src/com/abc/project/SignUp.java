package com.abc.project;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.entity.Model;

@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//fetch the data from html
		String un = request.getParameter("username");
		String email = request.getParameter("email");
		String pwd = request.getParameter("password");
		String cpwd = request.getParameter("cpassword");

		if(pwd.equals(cpwd))
		{
			Model checkModel = new Model();
			checkModel.setEmail(email);
			if(checkModel.checkEmail())
			{
				checkModel=null;
				RequestDispatcher dispatcher = request.getRequestDispatcher("/emailError.html");
				dispatcher.forward(request, response);
			}
			else
			{
				Model model = new Model();
				model.setUserName(un);
				model.setEmail(email);
				model.setPwd(pwd);

				if(model.signUp()) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/successSignUp.html");
					dispatcher.forward(request, response);
				}
				else
				{
					RequestDispatcher dispatcher = request.getRequestDispatcher("/failedSignUp.html");
					dispatcher.forward(request, response);
				}
			}

		}
		else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/signUpError.html");
			dispatcher.forward(request, response);

		}
	}

}
