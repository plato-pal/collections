package collections;

import java.nio.charset.StandardCharsets;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;

public class CollectionsQueueListener {
	
	public final static String COLLECTIONS_QUEUE = "collections-policy-queue";
	
	private final static ConnectionFactory CONNECTION_FACTORY = new ConnectionFactory();
	private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	private static DeliverCallback collectionsCallback = (consumerTag, delivery) -> {
		try {
			String strMessage = new String(delivery.getBody(), StandardCharsets.UTF_8);
			CollectionsNewPolicyMessage incomingMessage = OBJECT_MAPPER.readValue(strMessage, CollectionsNewPolicyMessage.class);
			System.out.println(String.format("Received: %s", incomingMessage.toString()));
		} catch(Exception e) {
			e.printStackTrace();
		}
	};
	
	public static void listen() {
		Connection connection;
		
		try {
			connection = CONNECTION_FACTORY.newConnection();
			Channel channel = connection.createChannel();
			
			channel.queueDeclare(COLLECTIONS_QUEUE, false, false, false, null);
			System.out.println("Collections queue declared and listening...");
			
			channel.basicConsume(COLLECTIONS_QUEUE, true, collectionsCallback, consumerTag -> {});
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
