package com.fxn.amqp.demo1;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者示例（简单队列）
 */
public class Consumer {

    private static final String queue = "test_queue";

    public static void main(String[] args) throws IOException, TimeoutException {

        //获取连接
        Connection connection = ConnectionUtil.getConnection();

        //从连接获取一个通道
        Channel channel = connection.createChannel();

        //队列声明
        channel.queueDeclare(queue, false, false, false, null);

        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "utf-8");
                System.out.println(msg);
            }
        };

        //监听队列
        channel.basicConsume(queue, true, defaultConsumer);

    }
}
