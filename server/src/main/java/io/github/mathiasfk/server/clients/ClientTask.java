package io.github.mathiasfk.server.clients;

import java.io.IOException;

import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import io.github.mathiasfk.common.Message;
import io.github.mathiasfk.server.validations.NicknameValidator;

public class ClientTask extends Thread {

    private final Socket client;
    private final ActiveClients activeClients;
    private final ObjectOutputStream selfOutStream;
    private String name;

    public ClientTask(Socket client, ActiveClients activeClients) throws IOException{
        this.client = client;
        this.activeClients = activeClients;
        this.selfOutStream = new ObjectOutputStream(client.getOutputStream());
    }

    public void run(){
        try{
            ObjectInputStream inStream = new ObjectInputStream(client.getInputStream());

            Message nicknameMsg;
            String nickname;
            while(true){
                nicknameMsg = (Message)inStream.readObject();
                nickname = nicknameMsg.getContent().trim();

                if (activeClients.contains(nickname)) {
                    sendToSelf("Sorry, this nickname is already taken, please choose another one:");
                    continue;
                }

                if (!NicknameValidator.validateNickname(nickname)){
                    sendToSelf("Sorry, this nickname is invalid, please choose another one:");
                    continue;
                }

                name = nickname;
                activeClients.put(nickname, this.selfOutStream);
                break;
            }

            sendToSelf("Welcome " + nickname + "! There are other " + (activeClients.size() - 1) + " people connected");
            sendToOthers(nickname + " just connected. Say them hi.");
        
            while(true){
                Message msg = (Message)inStream.readObject();
                String msgContent = msg.getContent();
                msg.setFrom(nickname);

                if (msgContent.equals("/exit")){
                    client.close();
                    sendToOthers(nickname + " left the chat");
                    activeClients.remove(nickname);
                    
                } else if (msg.isPvt()){
                    if (activeClients.contains(msg.getTo())){
                        sendToOne(msg);
                    } else {
                        sendToSelf("This user does not exist or is not connected.");
                    }

                } else {
                    sendToAll(msg);
                }
            }
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    private void sendToOthers(Message msg) throws IOException {
        var streams = activeClients.getStreamCollection();
        for(var each : streams){
            if(each != selfOutStream){
                each.writeObject(msg);
                each.flush();
            }
        }
    }

    private void sendToOthers(String content) throws IOException {
        Message msg = new Message("server","all",content,false);
        sendToOthers(msg);
    }

    private void sendToAll(Message msg) throws IOException {
        var streams = activeClients.getStreamCollection();
        for(var each : streams){
            each.writeObject(msg);
            each.flush();
        }
    }

    private void sendToOne(Message msg) throws IOException {
        var stream = activeClients.getStream(msg.getTo());
        stream.writeObject(msg);
        selfOutStream.writeObject(msg);
    }

    private void sendToSelf(Message msg) throws IOException {
        selfOutStream.writeObject(msg);
    }

    private void sendToSelf(String content) throws IOException {
        Message msg = new Message("server",name,content,false);
        sendToSelf(msg);
    }

}