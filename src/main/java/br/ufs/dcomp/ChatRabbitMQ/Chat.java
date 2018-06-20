package br.ufs.dcomp.ChatRabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.*;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import java.io.IOException;
import java.util.Scanner;
public class Chat {
    public static void main( String[] args ) throws Exception{
        String msg = "";
        Scanner input = new Scanner(System.in);
        Channel channel;
        Conexao con = new Conexao();
        con.createFactory();
        con.createCon();
        channel = con.createCh();
        Queue q = new Queue();
        con.login(q);
        q.createSvQueue(channel);
        Acoes cmd = new Acoes(q, con, msg);
        do{
            cmd.recept();
            System.out.print(cmd.getDest()+">> ");
            msg = input.nextLine();
            if (msg.length() > 0 )
                cmd.send(msg);
        }
        while(!(msg.equals("!quit")));
        con.close();
    }
}