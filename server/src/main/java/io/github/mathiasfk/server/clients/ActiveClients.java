package io.github.mathiasfk.server.clients;

import java.util.HashMap;
import java.util.Vector;
import java.util.Map;
import java.util.Collection;

import java.io.ObjectOutputStream;

public class ActiveClients {

    private Map<String,ObjectOutputStream> outputStreams = new HashMap<String,ObjectOutputStream>();

    public ActiveClients(){};

    public ObjectOutputStream getStream(String nickname){
        return outputStreams.get(nickname);
    }

    public boolean contains(String nickname){
        return outputStreams.keySet().contains(nickname);
    }

    public void put(String nickname, ObjectOutputStream outStream){
        outputStreams.put(nickname, outStream);
    }

    public void remove(String nickname){
        outputStreams.remove(nickname);
    }

    public int size(){
        return outputStreams.size();
    }

    public Collection<ObjectOutputStream> getStreamCollection(){
        return outputStreams.values();
    }
}
