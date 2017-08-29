package com.topbnt.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by longjinwen on 28/08/2017.
 */
public class RabbitMqRec {
    private static String QUEUE_NAME = "hello";
    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        try {
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            //consumer 默认定义了一个Consumer信息,这个类DefafultConsumer
            // 默认实现了Consumer接口,传入一个channel,告诉服务器我们需要那个
            // 个channel的消息 ，如果那个channel里有消息，就会执行回调函数handleDelivery
            Consumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body,"utf-8");
                    System.out.println("RabbitMqRec Received the message:" +message);
                }
            };
            //		自动回复队列应答 -- RabbitMQ中的消息确认机制，后面章节会详细讲解
            //消息 自动应答
            channel.basicConsume(QUEUE_NAME, true, consumer);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
