package io.github.mathiasfk.client.communication;

import java.io.ObjectOutputStream; 
import java.net.SocketException;
import java.io.BufferedReader; 
import java.io.InputStreamReader; 

import io.github.mathiasfk.common.Message;

public class OutgoingMessagesTask extends Thread {
    private final ObjectOutputStream outStream;

    public OutgoingMessagesTask(ObjectOutputStream outStream){
        this.outStream = outStream;
    }

    public void run(){
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
            
            boolean connected = true;

            while(connected){
                String textInput = reader.readLine().trim();
                String msgContent = textInput;
                String to = "all";
                boolean isPrivate = false;

                if (textInput.equals("/exit")){
                    connected = false;
                }
                if (textInput.equals("/help")){
                    printHelp();
                    continue;
                }
                if (textInput.startsWith("/p")){
                    String dest = textInput.split(" ")[1];
                    String pvtMsg = textInput.substring(dest.length() + 3).trim();
                    
                    msgContent = pvtMsg;
                    isPrivate = true;
                    to = dest;
                }

                Message msg = new Message("me",to,msgContent,isPrivate);
                outStream.writeObject(msg);
            }
        } catch(SocketException socketEx){
            // connection closed
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    private static void printHelp(){
        System.out.println("*** Available commands:");
        System.out.println(" /p <user> <msg>\tsend a private message to <user>.");
        System.out.println(" /help\t\tshows this help.");
        System.out.println(" /exit\t\tclose the chat.");
    }
}
