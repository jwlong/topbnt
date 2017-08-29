package com.topbnt.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by longjinwen on 28/08/2017.
 *  产生 多条消息
 */
public class MutilProduce {
    private static String QUEUE_NAME = "hello2";
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        // create message channel
        Channel channel = connection.createChannel();
        // this is publish the message,but at the fist must declare
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        for (int i = 0 ;i< 10 ;i++){
           // channel.basicPublish("exchange info",QUEUE_NAME);
        }

    }
}
