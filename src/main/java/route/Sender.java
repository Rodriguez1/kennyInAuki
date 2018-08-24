package route;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import util.ConnectionUtil;

/**
 * 
 *
 */
public class Sender {
	
	private final static String EXCHANGE_NAME = "testqute";
	
	public static void main(String[] args) throws Exception{
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();
		//����һ��·�ɸ�ʽ�Ľ�����
		channel.exchangeDeclare(EXCHANGE_NAME, "direct");
		channel.basicPublish(EXCHANGE_NAME, "key2",null,"·����Ϣ".getBytes());
		
		channel.close();
		connection.close();
	}

}