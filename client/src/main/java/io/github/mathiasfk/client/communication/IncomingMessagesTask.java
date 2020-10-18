package io.github.mathiasfk.client.communication;

import java.io.ObjectInputStream; 
import java.net.SocketException;

import io.github.mathiasfk.common.Message;

public class IncomingMessagesTask extends Thread {
    private final ObjectInputStream inStream;

    public IncomingMessagesTask(ObjectInputStream inStream){
        this.inStream = inStream;
    }

    public void run(){
        try{
            while(true){
                Message msg = (Message)inStream.readObject();
                System.out.println(formatMessage(msg));
            }
        } catch(SocketException socketEx){
            // connection closed
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    private String formatMessage(Message msg){
        String formatted = "";

        if (msg.getFrom().equals("server")) {
            formatted = "*** " + msg.getContent();
        }else{
            formatted += msg.getFrom() + " says";

            if (msg.isPvt()){
                formatted += " privately";
            }
            if (msg.getTo() != "all"){
                formatted += " to " + msg.getTo();
            }
            formatted += ":" + msg.getContent();
        }
        return formatted;
    }
}
