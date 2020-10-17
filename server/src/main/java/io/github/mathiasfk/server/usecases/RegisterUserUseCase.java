package io.github.mathiasfk.server.usecases;

import io.github.mathiasfk.domain.entities.User;

public class RegisterUserUseCase {

    public RegisterUserUseCase(final String someConfig) {
        System.out.println("Port configured: " + someConfig);
    }

    public User registerUser(final String nickname){
        System.out.println("User registered: " + nickname);
        return new User(nickname);
    }
}
