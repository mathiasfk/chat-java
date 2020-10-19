package io.github.mathiasfk.client.communication;

import java.io.ObjectInputStream; 
import java.net.SocketException;

import io.github.mathiasfk.common.Message;
import io.github.mathiasfk.client.cli.MessageFormatter;

public class IncomingMessagesTask extends Thread {
    private final ObjectInputStream inStream;

    public IncomingMessagesTask(ObjectInputStream inStream){
        this.inStream = inStream;
    }

    public void run(){
        try{
            while(true){
                Message msg = (Message)inStream.readObject();
                System.out.println(MessageFormatter.formatMessage(msg));
            }
        } catch(SocketException socketEx){
            // connection closed
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }
}
