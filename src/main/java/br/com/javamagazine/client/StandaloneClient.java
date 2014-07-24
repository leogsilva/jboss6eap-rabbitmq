package br.com.javamagazine.client;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import com.rabbitmq.client.Address;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class StandaloneClient {

	public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException, URISyntaxException, IOException {
		Connection conn = null;
		Channel channel = null;
		try {
			ConnectionFactory cf = new ConnectionFactory();
			cf.setUsername("guest");
			cf.setPassword("guest");
			conn = cf.newConnection(new Address[] { 
				new Address("127.0.0.1", 5672)
			});
			channel = conn.createChannel();
			String exchangeName = "javamagazine";
			String routingKey = "info1";
			byte[] messageBodyBytes = "Hello, world!".getBytes();
			channel.basicPublish(exchangeName, routingKey, null, messageBodyBytes);
		} finally {
			if (conn != null) conn.close();
			if (channel != null && channel.isOpen()) channel.close();
		}
	}
}
