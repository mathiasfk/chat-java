package io.github.mathiasfk.server.application;

import java.io.IOException;

import java.net.Socket;
import java.net.ServerSocket;
import java.io.ObjectOutputStream;

import java.util.HashMap;
import java.util.Vector;
import java.util.Map;

import io.github.mathiasfk.server.clients.ClientTask;

public class Main {

    private static final int PORT = 3000;

    private static Map<Socket,ObjectOutputStream> activeClients = new HashMap<Socket,ObjectOutputStream>();
    private static Vector<String> usedNicknames = new Vector<String>();

    public static void main(String[] args) throws IOException,ClassNotFoundException {
        System.out.println("Chat server running.");

        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Server listening at " + PORT);

        while(true){
            System.out.println("Waiting for connection...");
            Socket client = server.accept();
            activeClients.put(client, new ObjectOutputStream(client.getOutputStream()));

            ClientTask clientTask = new ClientTask(client, activeClients, usedNicknames);
            clientTask.start();

            System.out.println(activeClients.size() + " clients connected.");
        }
    }
}