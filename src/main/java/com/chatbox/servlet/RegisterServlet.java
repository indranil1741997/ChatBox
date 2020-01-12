package com.chatbox.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chatbox.model.User;
import com.chatbox.service.RegisterService;
import com.chatbox.service.RegisterServiceImpl;

/**
 * Servlet implementation class DatabaseConnection
 */
public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private RegisterService registerService;
	
	public RegisterServlet() {
		super();
		this.registerService = new RegisterServiceImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("register.jsp").include(request, response);
		
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String password = request.getParameter("psw");
		
		User user = new User(email,name,phone,password);
		if(registerService.validateParameters(user))
			out.print("Succesfully Registered!");
		else
			out.print("Sorry, Registration unsuccesfull!");
	}
}
