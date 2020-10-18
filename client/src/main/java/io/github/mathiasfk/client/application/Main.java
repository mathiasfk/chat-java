package io.github.mathiasfk.client.application;

import java.io.IOException;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.io.BufferedReader; 
import java.io.InputStreamReader; 

import io.github.mathiasfk.client.communication.*;

public class Main {

    private static final int SERVER_PORT = 3000;
    private static final String SERVER_ADDR = "127.0.0.1";

    public static void main(String[] args) throws IOException {
        System.out.print("Connecting... ");

        Socket client = new Socket(SERVER_ADDR, SERVER_PORT);

        ObjectOutputStream outStream = new ObjectOutputStream(client.getOutputStream());
        ObjectInputStream inStream = new ObjectInputStream(client.getInputStream());

        System.out.println("ok");
        System.out.println("*** Welcome! Please provide a nickname:");

        IncomingMessagesTask incomingMessagesTask = new IncomingMessagesTask(inStream);
        incomingMessagesTask.start();

        OutgoingMessagesTask outgoingMessagesTask = new OutgoingMessagesTask(outStream);
        outgoingMessagesTask.start();

        try{
            outgoingMessagesTask.join();
            incomingMessagesTask.join();
        }catch(InterruptedException ex){
            // interrupted with ctr+C
        }

        outStream.close();
        inStream.close();

        client.close();

        System.out.println("*** Disconnected. Bye!");
    }
}