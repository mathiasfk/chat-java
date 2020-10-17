package io.github.mathiasfk.client;

import io.github.mathiasfk.domain.entities.Message;
import io.github.mathiasfk.domain.entities.User;

import io.github.mathiasfk.client.config.TestConfig;
import io.github.mathiasfk.client.usecases.SendMessageUseCase;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 

public class Main {
    public static void main(String[] args) throws IOException,ClassNotFoundException {
        System.out.println("Welcome to the chat");

        // TestConfig config = new TestConfig();

        // Message message = new Message("Hi!");
        // User use = new User("mathiasfk");

        // SendMessageUseCase sendMessageUseCase = config.buildSendMessageUseCase();
        // sendMessageUseCase.sendMessage(message);

        Socket client = new Socket("127.0.0.1",3322);

        ObjectInputStream inStream = new ObjectInputStream(client.getInputStream());
        ObjectOutputStream outStream = new ObjectOutputStream(client.getOutputStream());
        
        String welcomeMsg = (String)inStream.readObject();
        System.out.println("Server says: " + welcomeMsg);
        System.out.println("Say something:");

        //Enter data using BufferReader 
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 

        // Reading data using readLine 
        String msg = reader.readLine();
        outStream.writeObject(msg);

        Date serverTime = (Date)inStream.readObject();
        System.out.println("Current time at server:" + serverTime.toString());

        inStream.close();
        System.out.println("Connection closed.");
    }
}
