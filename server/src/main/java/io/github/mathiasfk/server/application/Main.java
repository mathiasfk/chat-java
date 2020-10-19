package io.github.mathiasfk.server.application;

import java.io.IOException;

import java.net.Socket;
import java.net.ServerSocket;

import java.util.HashMap;
import java.util.Vector;
import java.util.Map;

import io.github.mathiasfk.server.clients.ClientTask;
import io.github.mathiasfk.server.clients.ActiveClients;

public class Main {

    private static final int PORT = 3000;

    public static void main(String[] args) throws IOException,ClassNotFoundException {
        System.out.println("Chat server running.");

        ActiveClients activeClients = new ActiveClients();
        ServerSocket server = new ServerSocket(PORT);

        System.out.println("Server listening at " + PORT);

        while(true){
            System.out.println("Waiting for connection...");
            Socket client = server.accept();

            System.out.println("Client connected: " + client.getInetAddress().getHostAddress());

            ClientTask clientTask = new ClientTask(client, activeClients);
            clientTask.start();
        }
    }
}