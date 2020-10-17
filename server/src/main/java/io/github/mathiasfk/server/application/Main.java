package io.github.mathiasfk.server;

import java.io.IOException;

import java.net.Socket;
import java.net.ServerSocket;
import java.util.Date;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {

    private static final int PORT = 3000;

    private static Map<Socket,ObjectOutputStream> activeClients = new HashMap<Socket,ObjectOutputStream>();

    public static void main(String[] args) throws IOException,ClassNotFoundException {
        System.out.println("Chat server running.");

        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Server listening at " + PORT);

        while(true){
            System.out.println("Waiting for connection...");
            Socket client = server.accept();
            activeClients.put(client, new ObjectOutputStream(client.getOutputStream()));

            ClientTask clientTask = new ClientTask(client, activeClients);
            clientTask.start();

            System.out.println(activeClients.size() + " clients connected.");
        }
    }
}

class ClientTask extends Thread {
    private final Socket client;
    private final Map<Socket,ObjectOutputStream> activeClients;
    private final ObjectOutputStream myOutStream;

    public ClientTask(Socket client, Map<Socket,ObjectOutputStream> activeClients){
        this.client = client;
        this.activeClients = activeClients;
        this.myOutStream = activeClients.get(client);
    }

    public void run(){
        try{
            System.out.println("Client connected: " + client.getInetAddress().getHostAddress());
            ObjectInputStream inStream = new ObjectInputStream(client.getInputStream());

            String nickname = "someone";

            sendToSelf("*** You are connected. Welcome!");
            sendToOthers("*** " + nickname + " just connected. Say them hi.");
        
            while(true){
                String msg = (String)inStream.readObject();
                sendToOthers(nickname + " says: " + msg);
            }
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    private void sendToOthers(String msg) throws IOException {
        for (Map.Entry<Socket,ObjectOutputStream> other : activeClients.entrySet())
            {
                if (other.getKey() != this.client)
                {
                    ObjectOutputStream otherOutStream = other.getValue();
                    otherOutStream.writeObject(msg);
                    otherOutStream.flush();
                }
            }
    }

    private void sendToSelf(String msg) throws IOException {
        myOutStream.writeObject(msg);
    }
}