package br.ufs.dcomp.Chat;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.*;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;

import java.util.Scanner;

public class Chat {
    public static void main( String[] args ) throws Exception{
        String queue, user;
        
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("amqp://pfcfsodm:vS2L9b7OyXh6ZeyQvKf72ZYSOVWGyCiE@porpoise.rmq.cloudamqp.com/pfcfsodm");
        factory.setVirtualHost("/");
        Connection connection = factory.newConnection();
        System.out.println("[INFO] Criando conexão com o servidor .................... [OK!]");
        
        Channel channel = connection.createChannel();
        System.out.println("[INFO] Criando canal ..................................... [OK!]");
        
        Scanner input = new Scanner(System.in);
        System.out.print( "\nUser: ");
        user = input.next();
        queue = user + "-queue";
        
        Queue q = new Queue(queue);
        
        q.createSvQueue(channel, q.getQueueName());
    }
    
    public void closeCon(Channel channel, Connection connection) throws Exception{
        channel.close();
        System.out.println("[INFO] Fechando canal .................................... [OK!]");
        connection.close(); 
        System.out.println("[INFO] Fechando conexão com o servidor ................... [OK!]");
    }
    
    public void send(Channel channel, String msg, String queue) throws Exception{
        msg = "(Data | Hora) " + msg;
        channel.basicPublish("", queue, null, msg.getBytes());
        System.out.println("[INFO] Enviando mensagem ..................................... [OK!]");
    }
    public void recept(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body, Channel channel) throws Exception{
        Consumer consumer = new DefaultConsumer(channel);
        String message = new String(body, "UTF-8");
        System.out.println("[INFO] Recebendo mensagem .................................... [OK!]");
    }
}