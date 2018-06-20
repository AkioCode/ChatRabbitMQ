package br.ufs.dcomp.ChatRabbitMQ;

import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.util.Scanner;

public class Queue{
        private String queue;
        private String destQueue;
        
        Conexao con = new Conexao();
        
        public Queue(){}
        
        public Queue(String queue){
            this.queue = queue;
            this.destQueue = destQueue;
        }
    
        public void createSvQueue(Channel channel) throws IOException{
            channel.queueDeclare(getQueueName(), false, false, false, null);
            System.out.println("[INFO] Criando fila ..................................... [OK!]");
        }
        
        
        public void setQueueName(String newQName){
                queue = newQName;
        }
        
        public String getQueueName(){
            return queue;
        }
        
        public void setDestQueue(String newQName){
                destQueue = newQName;
        }
        
        public String getDestQueue(){
            return destQueue;
        }
}