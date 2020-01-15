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
	private Messages message;
	
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

		String conv_id = messageService.getConversationId(chatUsername, sessionUsername);
		message = null;
		
		if(ADD_MESSAGE.equals(request.getParameter(ACTION))) {
			String msg = (String) request.getAttribute("message");
			messageService.addMessage(msg, conv_id);
		}
		else if(GET_MESSAGE.equals(request.getParameter(ACTION))) {
			message = messageService.getMessage(conv_id);
		}
		
		request.setAttribute("message", messageService.populateMessageList(message));
		request.getRequestDispatcher("welcome.jsp").forward(request, response);
	}
}
