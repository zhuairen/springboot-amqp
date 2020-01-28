package com.fxn.amqp.demo1;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtil {


    /**
     * 获取rabbitmq的信息
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    public static Connection getConnection() throws IOException, TimeoutException {

        //定义一个连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置服务地址
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("fxn");
        connectionFactory.setPassword("123");
        connectionFactory.setVirtualHost("/host_fxn");

        Connection connection = connectionFactory.newConnection();
        return connection;
    }
}
