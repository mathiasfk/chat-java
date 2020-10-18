package io.github.mathiasfk.server.clients;

import java.io.IOException;

import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.Vector;
import java.util.Map;

import io.github.mathiasfk.common.Message;

public class ClientTask extends Thread {
    private final Socket client;
    private final Map<Socket,ObjectOutputStream> activeClients;
    private final ObjectOutputStream selfOutStream;
    private final Vector<String> usedNicknames;

    public ClientTask(Socket client, Map<Socket,ObjectOutputStream> activeClients, Vector<String> usedNicknames){
        this.client = client;
        this.activeClients = activeClients;
        this.selfOutStream = activeClients.get(client);
        this.usedNicknames = usedNicknames;
    }

    public void run(){
        try{
            System.out.println("Client connected: " + client.getInetAddress().getHostAddress());
            ObjectInputStream inStream = new ObjectInputStream(client.getInputStream());

            Message nicknameMsg;
            String nickname;
            while(true){
                nicknameMsg = (Message)inStream.readObject();
                nickname = nicknameMsg.getContent();
                if (usedNicknames.contains(nickname)) {
                    sendToSelf("*** Sorry, this nickname is already taken, please choose another one:");
                } else {
                    usedNicknames.add(nickname);
                    break;
                }
            }

            sendToSelf("*** Welcome! " + nickname + ". There are other " + (activeClients.size() - 1) + " people connected");
            sendToOthers("*** " + nickname + " just connected. Say them hi.");
        
            while(true){
                Message msg = (Message)inStream.readObject();
                String msgContent = msg.getContent();

                if (msgContent.equals("/exit")){
                    client.close();
                    sendToOthers("*** " + nickname + " left the chat");
                    activeClients.remove(client);
                    
                } else if (msg.isPvt()){
                    sendToAll(nickname + " says to " + msg.getTo() + ": " + msgContent);

                } else {
                    sendToAll(nickname + " says: " + msgContent);
                }
            }
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    private void sendToOthers(String content) throws IOException {
        Message msg = new Message("someone","all",content,false);
        for (Map.Entry<Socket,ObjectOutputStream> other : activeClients.entrySet()) {
                if (other.getKey() != this.client) {
                    ObjectOutputStream otherOutStream = other.getValue();
                    otherOutStream.writeObject(msg);
                    otherOutStream.flush();
                }
            }
    }

    private void sendToAll(String content) throws IOException {
        Message msg = new Message("someone","all",content,false);
        for (Map.Entry<Socket,ObjectOutputStream> other : activeClients.entrySet()) {
                ObjectOutputStream otherOutStream = other.getValue();
                otherOutStream.writeObject(msg);
                otherOutStream.flush();
            }
    }

    private void sendToSelf(String content) throws IOException {
        Message msg = new Message("someone","self",content,false);
        selfOutStream.writeObject(msg);
    }
}