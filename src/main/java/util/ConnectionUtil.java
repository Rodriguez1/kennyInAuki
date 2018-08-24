package util;

/**
 * @author  kenny zhai
 * 2018-8-13 11:43:32
 *
 * 用于创建连接 的工具类
 */
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

public class ConnectionUtil {
    public static Connection getConnection() throws Exception{
        ConnectionFactory conn = new ConnectionFactory();
        conn.setHost("192.168.0.174");
        conn.setPort(5672);
        conn.setUsername("test");
        conn.setPassword("test");
        conn.setVirtualHost("/test");
        return conn.newConnection();    //创建一个新的连接
    }

}
