package work;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

import util.ConnectionUtil;

public class Reciver2  {
	
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
				
				System.out.println("������2�յ�������Ϊ��"+new String(body));
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				channel.basicAck(envelope.getDeliveryTag(), false);//����2 false ȷ���յ���Ϣ��true �ܾ��յ���Ϣ
				
			}
		};
		//ע��������
		//����2���ֶ�ȷ�ϣ��յ���Ϣ����Ҫ�ֶ����߷����������յ���Ϣ
		
		channel.basicConsume(QUEUE, false,consumer);
		
		
	}

}