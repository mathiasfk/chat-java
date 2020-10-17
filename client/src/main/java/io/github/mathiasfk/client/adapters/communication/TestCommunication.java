package io.github.mathiasfk.client.adapters.communication;

import io.github.mathiasfk.client.usecases.ports.Communication;

public class TestCommunication implements Communication {
    
    @Override
    public boolean send(){
        return true;
    }
}