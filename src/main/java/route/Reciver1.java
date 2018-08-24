package route;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

import util.ConnectionUtil;

public class Reciver1 {
	
	private final static String EXCHANGE_NAME = "testqute";
	
	public static void main(String[] args) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		final Channel channel = connection.createChannel();
		channel.queueDeclare("testroutequeue1", false, false, false, null);
		//�󶨶��е�������
		//�󶨵�������ʱָ��һ����ǣ�ֻ�к�key1һ���ı�ǵ���Ϣ�Żᱻ��ǰ�������߽���
		channel.queueBind("testroutequeue1", EXCHANGE_NAME, "key1");
		//�����Ҫ�����ǣ��ٴ�ִ��
		channel.queueBind("testroutequeue1", EXCHANGE_NAME, "key2");
		channel.basicQos(1);
		DefaultConsumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
//				super.handleDelivery(consumerTag, envelope, properties, body);
				System.out.println("������1"+new String(body));
				channel.basicAck(envelope.getDeliveryTag(), false);
			}
		};
		channel.basicConsume("testroutequeue1", false,consumer);
		
		
		
	}

}