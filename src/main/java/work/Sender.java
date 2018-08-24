package work;

import com.rabbitmq.client.Connection;

import util.ConnectionUtil;

import com.rabbitmq.client.Channel;

/**
 * ����������
 */
public class Sender {
    private final static String QUEUE = "testwork"; //��������

    public static void main(String [] args)throws Exception{
        /**
         * 1����ȡ����
         * 2������ͨ��
         * 3����������
         * 4����������
         * 5���رն���
         */
        // 1
        Connection connection = ConnectionUtil.getConnection();
        // 2
        Channel channel = connection.createChannel();
        // 3
        /**
         * 1:
         * 2:
         * 3:
         * 4:
         * 5:
         */
        channel.queueDeclare(QUEUE,false,false,false,null);
        for (int i = 0; i < 100; i++) {
			channel.basicPublish("", QUEUE, null, ("���͵���Ϣ"+i).getBytes());
		}
		//        channel.baspub("",QUEUE,null,"���ͺ���Ϣ".getBytes());
        channel.close();
        connection.close();
    }
}












