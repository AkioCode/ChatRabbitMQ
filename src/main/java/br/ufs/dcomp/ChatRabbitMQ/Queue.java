package br.ufs.dcomp.Chat;

import com.rabbitmq.client.Channel;

public class Queue{
        private String queue;
        
        public Queue(String queue){
            this.queue = queue;
        }
    
        public void createSvQueue(Channel channel, String QUEUE_NAME) throws Exception{
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        }
        
        
        public void setQueueName(String newQName){
                queue = newQName;
        }
        
        public String getQueueName(){
            return queue;
        }
}