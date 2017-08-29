package com.topbnt.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by longjinwen on 28/08/2017.
 * 消息的发送者
 */
public class RabbitMqSend {
    private static String QUEUE_NAME = "hello";
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        try {

            connectionFactory.setHost("localhost");
            Connection connection = connectionFactory.newConnection();
            //创建一个频道
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);

            String message = "hello world rabbitmq !";
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes("utf-8"));
            System.out.println(" P send message :"+ message);
            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
