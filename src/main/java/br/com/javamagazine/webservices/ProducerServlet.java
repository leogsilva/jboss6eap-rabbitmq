package br.com.javamagazine.webservices;

import java.io.IOException;

import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.QueueConnection;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.qpid.proton.ProtonFactory;
import org.apache.qpid.proton.ProtonFactoryLoader;
import org.apache.qpid.proton.amqp.messaging.AmqpValue;
import org.apache.qpid.proton.message.Message;
import org.apache.qpid.proton.message.MessageFactory;
import org.apache.qpid.proton.messenger.Messenger;
import org.apache.qpid.proton.messenger.MessengerFactory;
import org.apache.qpid.proton.messenger.impl.MessengerFactoryImpl;

/**
 * Servlet implementation class ProducerServlet
 */
@WebServlet("/ProducerServlet")
public class ProducerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject @RabbitMessaging MessagingProducer rabbitProducer;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProducerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MessengerFactory messengerFactory = new ProtonFactoryLoader<MessengerFactory>(MessengerFactory.class).loadFactory();
		MessageFactory messageFactory = new ProtonFactoryLoader<MessageFactory>(MessageFactory.class).loadFactory();
		
		Messenger messenger = messengerFactory.createMessenger();
		messenger.start();
		
		Message message = messageFactory.createMessage();
		message.setAddress("127.0.0.1:5672/helloQueue");
		message.setSubject("teste");
		message.setBody(new AmqpValue("teste"));
		messenger.put(message);
		messenger.send();
		messenger.stop();
		response.getWriter().write("OK again");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
