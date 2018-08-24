package helloworld;

import com.rabbitmq.client.Connection;

import util.ConnectionUtil;

import com.rabbitmq.client.Channel;

/**
 * ����������
 */
public class Sender {
    private final static String QUEUE = "testhello"; //��������

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
         */
        channel.queueDeclare(QUEUE,false,false,false,null);
        channel.basicPublish("",QUEUE,null,"���͵���Ϣ".getBytes());
//        channel.baspub("",QUEUE,null,"���ͺ���Ϣ".getBytes());
        channel.close();
        connection.close();
    }
}