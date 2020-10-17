package io.github.mathiasfk.server.config;

import io.github.mathiasfk.server.usecases.RegisterUserUseCase;

public class TestConfig {
    
    public RegisterUserUseCase buildRegisterUserUseCase() {
        return new RegisterUserUseCase("configured");
    }
}
