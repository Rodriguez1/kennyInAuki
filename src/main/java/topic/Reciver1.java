package topic;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

import util.ConnectionUtil;

public class Reciver1 {
	
	private final static String EXCHANGE_NAME = "testtopic";
	
	public static void main(String[] args) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		final Channel channel = connection.createChannel();
		channel.queueDeclare("testtopicqueue1", false, false, false, null);
		//绑定队列到交换机
		//绑定到交换机时指定一个标记，只有和key1一样的标记的消息才会被当前的消费者接收
		channel.queueBind("testtopicqueue1", EXCHANGE_NAME, "key.*");
		//如果需要多个标记，再次执行
		channel.queueBind("testtopicqueue1", EXCHANGE_NAME, "abc.#");
		channel.basicQos(1);
		DefaultConsumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
//				super.handleDelivery(consumerTag, envelope, properties, body);
				System.out.println("消费者1"+new String(body));
				channel.basicAck(envelope.getDeliveryTag(), false);
			}
		};
		channel.basicConsume("testtopicqueue1", false,consumer);
		
		
		
	}

}
