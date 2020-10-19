package io.github.mathiasfk.client.cli;

import io.github.mathiasfk.common.Message;

public class MessageFormatter {
    private MessageFormatter(){};

    public static String formatMessage(Message msg){
        String formatted = "";

        if (msg.getFrom().equals("server")) {
            formatted = "*** " + msg.getContent();
        }else{
            formatted += msg.getFrom() + " says";

            if (msg.isPvt()){
                formatted += " privately";
            }
            if (!msg.getTo().equals("all")){
                formatted += " to " + msg.getTo();
            }
            formatted += ": " + msg.getContent();
        }
        return formatted;
    }
}
