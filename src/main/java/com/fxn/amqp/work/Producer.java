package com.fxn.amqp.work;

import com.fxn.amqp.single.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;


/**
 * 这里又一个轮询分发知识点，和负载均衡一样
 */
public class Producer {

    private static final String queue = "test_work_queue";

    public static void main(String[] args) throws IOException, TimeoutException {

        //获取连接
        Connection connection = ConnectionUtil.getConnection();

        //从连接获取一个通道
        Channel channel = connection.createChannel();

        //创建队列声明
        channel.queueDeclare(queue,false,false,false,null);

        for(int i=0; i<50;i++){
            String msg = "hello "+i+ " " + new Date();

            channel.basicPublish("",queue,null,msg.getBytes());
        }

        channel.close();
        connection.close();
    }
}
