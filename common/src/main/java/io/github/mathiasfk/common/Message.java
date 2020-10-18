package io.github.mathiasfk.common;

import java.io.Serializable;

public class Message implements Serializable {
    private String from;
    private String to;
    private String content;
    private boolean pvt;

    public Message(
        String from,
        String to,
        String content,
        boolean pvt
        ){
            this.from = from;
            this.to = to;
            this.content = content;
            this.pvt = pvt;
    }

    public String getFrom(){ return from; }
    public String getTo(){ return to; }
    public String getContent(){ return content; }
    public boolean isPvt(){ return pvt; }
}
