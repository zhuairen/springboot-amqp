package com.fxn.amqp.work;

import com.fxn.amqp.single.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer2 {

    private static final String queue = "test_work_queue";

    public static void main(String[] args) throws IOException, TimeoutException {

        //获取连接
        Connection connection = ConnectionUtil.getConnection();

        //从连接获取一个通道
        Channel channel = connection.createChannel();

        //队列声明
        channel.queueDeclare(queue, false, false, false, null);

        //定义消费者
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                String msg = new String(body,"utf-8");
                System.out.println("Consumer2 "+ msg);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
            }
        };

        boolean autoAck = true;
        channel.basicConsume(queue,autoAck,defaultConsumer);
    }
}
