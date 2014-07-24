package br.com.javamagazine.webservices;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;

/**
 * Responsible for produzing jms resources no sistema
 * 
 */
@RabbitMessaging
public class MessagingProducer {

	@Resource(name = "ConnectionFactory")
	private QueueConnectionFactory rabbitConnectionFactory;

	@Resource(name = "ExampleQueue")
	private Queue exampleQueue;

	public QueueConnection createConnection() throws JMSException {
		return rabbitConnectionFactory.createQueueConnection();
	}

	public QueueSession createExampleSession(QueueConnection con)
			throws JMSException {
		return con.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
	}
	
	public Queue getExampleQueue() {
		return exampleQueue;
	}

	public void closeOrderSession( QueueConnection conn)
			throws JMSException {
		conn.close();
	}

	public void closeOrderSession( QueueSession session)
			throws JMSException {
		session.close();
	}
}
