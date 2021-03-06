package com.chatbox.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chatbox.service.AvailableUserService;
import com.chatbox.service.AvailableUserServiceImpl;

public class UserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;  
	private AvailableUserService availableUser;
    private List<String> userList;
	
    public UserServlet() {
        super();
        this.availableUser = new AvailableUserServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		userList = new ArrayList<String>();
		String currentUser = (String) request.getSession(false).getAttribute("user");
		if(!availableUser.getAvailableUsers(userList, currentUser))
		{
			userList = null;
		}
		request.setAttribute("userList", userList);
		request.getRequestDispatcher("welcome.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
