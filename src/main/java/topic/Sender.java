package topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import util.ConnectionUtil;

public class Sender {
	
	private final static String EXCHANGE_NAME = "testtopic";
	
	public static void main(String[] args) throws Exception{
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME, "topic");
		channel.basicPublish(EXCHANGE_NAME, "abc.key.1", null, "topic 模式信息".getBytes());
		channel.close();
		connection.close();
	}

}
