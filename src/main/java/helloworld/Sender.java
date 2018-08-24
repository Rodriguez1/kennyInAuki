package helloworld;

import com.rabbitmq.client.Connection;

import util.ConnectionUtil;

import com.rabbitmq.client.Channel;

/**
 * 创建生产者
 */
public class Sender {
    private final static String QUEUE = "testhello"; //队列名称

    public static void main(String [] args)throws Exception{
        /**
         * 1：获取连接
         * 2：创建通道
         * 3：声明队列
         * 4：发送内容
         * 5：关闭队列
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
        channel.basicPublish("",QUEUE,null,"发送的消息".getBytes());
//        channel.baspub("",QUEUE,null,"发送胡消息".getBytes());
        channel.close();
        connection.close();
    }
}
