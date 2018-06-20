package br.ufs.dcomp.ChatRabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.Scanner;

public class Conexao{
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;
    private String user;
    public Conexao(){
        
    }
    public Conexao(String user){
        this.user = user;
    }
    
    //Cria factory, connection e channel
    public void createFactory() throws Exception{
        factory = new ConnectionFactory();
        factory.setUri("amqp://pfcfsodm:vS2L9b7OyXh6ZeyQvKf72ZYSOVWGyCiE@porpoise.rmq.cloudamqp.com/pfcfsodm");
    }
    public void createCon() throws Exception{
        connection = factory.newConnection();
        System.out.println("[INFO] Criando conexão com o servidor .................... [OK!]");
    }
    
    public Channel createCh() throws Exception{
        channel = connection.createChannel();
        System.out.println("[INFO] Criando canal ..................................... [OK!]");   
        return channel;
    }
    
    public void login(Queue q){
        //Login do usuário
        Scanner input = new Scanner(System.in);
        System.out.print( "\nUser: ");
        user = input.nextLine();
        if(user.length() == 0){
            System.out.println("Por favor, digite seu UserName");
            login(q);
        }
        q.setQueueName(user + "-queue");
    }
    
    public Channel getCh(){
        return channel;
    }
    
    public void setUser(String newUser){
        user = newUser;
    }
    
    public String getUser(){
        return user;
    }
    
    public void close() throws Exception{
        channel.close();
        System.out.println("[INFO] Fechando canal .................................... [OK!]");
        connection.close(); 
        System.out.println("[INFO] Fechando conexão com o servidor ................... [OK!]");
    }
}