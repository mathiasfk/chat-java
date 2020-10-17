package io.github.mathiasfk.server;

import io.github.mathiasfk.domain.entities.User;

import io.github.mathiasfk.server.config.TestConfig;
import io.github.mathiasfk.server.usecases.RegisterUserUseCase;

import java.io.IOException;

import java.net.Socket;
import java.net.ServerSocket;
import java.util.Date;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args) throws IOException,ClassNotFoundException {
        System.out.println("Chat server running.");

        // TestConfig config = new TestConfig();

        // RegisterUserUseCase registerUserUseCase = config.buildRegisterUserUseCase();
        // registerUserUseCase.registerUser("mathiasfk");

        ServerSocket server = new ServerSocket(3322);
        System.out.println("Server listening at 3322");

        while(true){
            Socket client = server.accept();
            System.out.println("Client connected: " + client.getInetAddress().getHostAddress());
            ObjectOutputStream outStream = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream inStream = new ObjectInputStream(client.getInputStream());
            outStream.flush();

            outStream.writeObject("You are connected. Welcome!");

            String msgFromClient = (String)inStream.readObject();
            System.out.println("Client says: " + msgFromClient);

            outStream.writeObject(new Date());
            outStream.close();
            client.close();
        }
        


    }
}
