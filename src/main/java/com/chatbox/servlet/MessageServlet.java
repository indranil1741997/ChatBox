package com.chatbox.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chatbox.model.Messages;
import com.chatbox.service.MessageService;
import com.chatbox.service.MessageServiceImpl;
import com.chatbox.utility.SessionUtility;

/**
 * Servlet implementation class ConversationId
 */
public class MessageServlet extends HttpServlet {

	private static final String GET_MESSAGE = "getMessage";
	private static final String ACTION = "action";
	private static final String ADD_MESSAGE = "addMessage";
	private static final long serialVersionUID = 1L;

	private MessageService messageService;
	
	public MessageServlet() {
		super();
		this.messageService = new MessageServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = SessionUtility.validateSession(request, response);
		
		String chatUsername = (String) request.getParameter("userTo");
		String sessionUsername = (String) session.getAttribute("name");
		String msg = (String) request.getAttribute("message");
		
		Messages message = new Messages(chatUsername, sessionUsername);
		message.addMessage(msg);
		
		if(ADD_MESSAGE.equals(request.getParameter(ACTION))) {
			messageService.addMessage(message);
		}
		else if(GET_MESSAGE.equals(request.getParameter(ACTION))) {
			messageService.getMessage(chatUsername, sessionUsername);
		}
	}
}
