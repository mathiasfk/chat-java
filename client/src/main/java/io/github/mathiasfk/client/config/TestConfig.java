package io.github.mathiasfk.client.config;

import io.github.mathiasfk.client.usecases.ports.Communication;
import io.github.mathiasfk.client.adapters.communication.TestCommunication;
import io.github.mathiasfk.client.usecases.SendMessageUseCase;

public class TestConfig {
    
    private final Communication comm = new TestCommunication();
    
    public SendMessageUseCase buildSendMessageUseCase() {
        return new SendMessageUseCase(comm);
    }
}
