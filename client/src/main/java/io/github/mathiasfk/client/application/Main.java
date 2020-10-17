package io.github.mathiasfk.client;

import io.github.mathiasfk.domain.entities.Message;
import io.github.mathiasfk.domain.entities.User;

import io.github.mathiasfk.client.config.TestConfig;
import io.github.mathiasfk.client.usecases.SendMessageUseCase;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the chat");

        TestConfig config = new TestConfig();

        Message message = new Message("Hi!");
        User use = new User("mathiasfk");

        SendMessageUseCase sendMessageUseCase = config.buildSendMessageUseCase();
        sendMessageUseCase.sendMessage(message);

    }
}
