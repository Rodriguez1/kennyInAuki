package work;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

import util.ConnectionUtil;
	
public class Reciver1  {
	
	private final static String QUEUE = "testwork";
	
	public static void main(String[] args)throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		final Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE, false, false, false, null);
		channel.basicQos(1);
		DefaultConsumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
//				super.handleDelivery(consumerTag, envelope, properties, body);
				
				System.out.println("消费者1收到的内容为："+new String(body));
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				channel.basicAck(envelope.getDeliveryTag(), false);//参数2 false 确认收到消息，true 拒绝收到消息
				
			}
		};
		//注册消费者
		//参数2，手动确认，收到消息后需要手动告诉服务器，已收到消息
		
		channel.basicConsume(QUEUE, false,consumer);
		
		
	}

}
