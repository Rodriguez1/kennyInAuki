package publish;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import util.ConnectionUtil;

public class Sender {
	
	private final static String EXCHANGE_NAME = "testexchange";
	
	public static void main(String[] args) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();
		//����һ����������������fanout,��������ģʽ
		channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
		//��������ģʽ������Ϣ�������������У��������ޱ��湦�ܣ����������ߣ���Ϣ����ʧ
		channel.basicPublish(EXCHANGE_NAME, "", null, "��������ģʽ����Ϣ".getBytes());
		channel.close();
		connection.close();
		
	}

}