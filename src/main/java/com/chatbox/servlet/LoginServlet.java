package com.chatbox.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chatbox.model.SessionInfo;
import com.chatbox.service.LoginService;
import com.chatbox.service.LoginServiceImpl;

public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private LoginService loginService;
	
	public LoginServlet() {
		super();
		this.loginService = new LoginServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("index.jsp").include(request, response);

		String email = request.getParameter("email");
		String password = request.getParameter("psw");
		
		SessionInfo sessionInfo = new SessionInfo();
		
		if(loginService.validateLogin(email,password,sessionInfo))
		{
			HttpSession session = request.getSession();
			session.setAttribute("user", sessionInfo.getEmail());
			session.setAttribute("name", sessionInfo.getName());
			response.sendRedirect(request.getContextPath() + "/welcome.jsp");		
		}
		else {
			out.print("Sorry, username or password err!");
			request.getRequestDispatcher("index.jsp").include(request, response);
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
		out.close();
	}
}
