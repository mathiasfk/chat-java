package io.github.mathiasfk.client.usecases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import io.github.mathiasfk.client.config.TestConfig;
import io.github.mathiasfk.domain.entities.Message;

public class SendMessageUseCaseTest {

    private final TestConfig config = new TestConfig();
    private final SendMessageUseCase sendMessageUseCase = config.buildSendMessageUseCase();

    @Test
    void sendMessageTest(){
        Message message = new Message("Hi!");
        assertTrue(sendMessageUseCase.sendMessage(message));
    }
    
}
