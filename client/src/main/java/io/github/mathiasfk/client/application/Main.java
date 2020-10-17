package io.github.mathiasfk.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.net.SocketException;

public class Main {

    private static final int SERVER_PORT = 3000;
    private static final String SERVER_ADDR = "127.0.0.1";

    public static void main(String[] args) throws IOException,ClassNotFoundException {
        System.out.print("Connecting... ");

        Socket client = new Socket(SERVER_ADDR, SERVER_PORT);

        ObjectOutputStream outStream = new ObjectOutputStream(client.getOutputStream());
        ObjectInputStream inStream = new ObjectInputStream(client.getInputStream());

        System.out.println("ok");
        System.out.println("*** Welcome! Please provide a nickname:");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
        String nickname = reader.readLine();
        outStream.writeObject(nickname);

        IncomingMsgsTask incomingMsgsTask = new IncomingMsgsTask(inStream);
        incomingMsgsTask.start();

        boolean connected = true;

        while(connected){
            String msg = reader.readLine();
            if (msg.trim().equals("/exit")){
                connected = false;
            }
            outStream.writeObject(msg);
        }
        outStream.close();
        inStream.close();
        client.close();

        System.out.println("*** Disconnected. Bye!");
    }
}

class IncomingMsgsTask extends Thread {
    private final ObjectInputStream inStream;

    public IncomingMsgsTask(ObjectInputStream inStream){
        this.inStream = inStream;
    }

    public void run(){
        try{
            while(true){
                String msg = (String)inStream.readObject();
                System.out.println(msg);
            }
        } catch(SocketException socketEx){
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }
}