package publish;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

import util.ConnectionUtil;

public class Reciver2 {
	
	private final static String EXCHANGE_NAME = "testexchange";
	
	public static void main(String[] args) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		final Channel channel = connection.createChannel();
		channel.queueDeclare("testpubqueue2", false, false, false, null);
		//绑定队列到交换机
		channel.queueBind("testpubqueue2", EXCHANGE_NAME, "");
		channel.basicQos(1);
		DefaultConsumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
//				super.handleDelivery(consumerTag, envelope, properties, body);
				System.out.println("消费者2"+new String(body));
				channel.basicAck(envelope.getDeliveryTag(), false);
			}
		};
		channel.basicConsume("testpubqueue2", false,consumer);
		
		
		
	}

}
