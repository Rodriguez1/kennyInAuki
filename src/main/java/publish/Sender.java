package publish;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import util.ConnectionUtil;

public class Sender {
	
	private final static String EXCHANGE_NAME = "testexchange";
	
	public static void main(String[] args) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();
		//定义一个交换机，类型是fanout,发布订阅模式
		channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
		//发布订阅模式，将消息发布到交换机中，交换机无保存功能，若无消费者，消息将丢失
		channel.basicPublish(EXCHANGE_NAME, "", null, "发布订阅模式的消息".getBytes());
		channel.close();
		connection.close();
		
	}

}
