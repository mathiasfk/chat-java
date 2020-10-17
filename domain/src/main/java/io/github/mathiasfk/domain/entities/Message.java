package io.github.mathiasfk.domain.entities;

//import io.github.mathiasfk.domain.entities.User;
//import io.github.mathiasfk.domain.entities.Room;

public class Message {
    private String id;
    // private User from;
    // private User to;
    // private Room room;
    
    private String content;

    public Message(String content){
        this.content = content;
        this.id = "some id";
    }

    public String getContent(){
        return this.content;
    }

}
