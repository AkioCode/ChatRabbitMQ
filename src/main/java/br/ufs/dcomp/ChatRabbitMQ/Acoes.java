package br.ufs.dcomp.ChatRabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.*;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import java.io.IOException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.Scanner;
import java.util.Date;


public class Acoes{
    private String msg;
    private String dest = "";
    private Queue q;
    private Conexao con;
    
    public Acoes(){};
    public Acoes(Queue q, Conexao con, String msg){
        this.q = q;
        this.con = con;
        this.msg = msg;
    }
    
    public void send(String msg) throws IOException{
        //Setting the time zone
        SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    	dateTimeInGMT.setTimeZone(TimeZone.getTimeZone("America/Bahia"));
    	String dH = dateTimeInGMT.format(new Date());
    	String arr[] = new String[2];
    	arr = dH.split(" ");
    	dH = "("+arr[0] +" às "+ arr[1]+") ";
    	
        if(msg.charAt(0) == '@' && msg.length() > 1)
            changeDest(msg);
        else if(msg.charAt(0) != '@'){
            msg = dH + con.getUser() + " diz: " + msg;
            if(q.getDestQueue()!=null) 
                (con.getCh()).basicPublish("", q.getDestQueue(), null, msg.getBytes());
        }
    }
    public void changeDest(String msg){
        q.setDestQueue(msg.substring(1,msg.length())+"-queue");
        setDest(msg);
    }
    public void recept() throws IOException{
        Consumer consumer = new DefaultConsumer(con.getCh()) {
            public void handleDelivery(String consumerTag, Envelope envelope,
                AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println("\n"+message);
                    System.out.print(">> ");
                }
            };
        (con.getCh()).basicConsume(q.getQueueName(), true, consumer);
    }
    
    
    public void setMsg(String newMsg){
        msg = newMsg;
    }
    public String getMsg(){
        return msg;
    }
    
    public void setDest(String newDest){
        dest = newDest;
    }
    public String getDest(){
        return dest;
    }
}