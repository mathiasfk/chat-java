package io.github.mathiasfk.client.usecases;

import io.github.mathiasfk.client.usecases.ports.Communication;
import io.github.mathiasfk.domain.entities.Message;

public class SendMessageUseCase {

    private final Communication comm;

    public SendMessageUseCase(final Communication comm) {
        this.comm = comm;
    }

    public boolean sendMessage(final Message msg){
        System.out.println("Message sent: " + msg.getContent());
        return comm.send();
    }

}
