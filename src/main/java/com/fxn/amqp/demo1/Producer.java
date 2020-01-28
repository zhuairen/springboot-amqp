package com.fxn.amqp.demo1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

/**
 * 生产者示例（简单队列）
 */
public class Producer {

    private static final String queue = "test_queue";

    public static void main(String[] args) throws IOException, TimeoutException {

        //获取连接
        Connection connection = ConnectionUtil.getConnection();

        //从连接获取一个通道
        Channel channel = connection.createChannel();

        //创建队列声明
        channel.queueDeclare(queue,false,false,false,null);

        String msg = "hello " + new Date();

        channel.basicPublish("",queue,null,msg.getBytes());


        channel.close();
        connection.close();
    }
}
