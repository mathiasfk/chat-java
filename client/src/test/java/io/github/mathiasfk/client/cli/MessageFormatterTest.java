package io.github.mathiasfk.client.cli;

import org.junit.jupiter.api.Test;

import io.github.mathiasfk.common.Message;

import static org.junit.jupiter.api.Assertions.*;

public class MessageFormatterTest {

    @Test
    void publicMessageToAll(){
        Message msg = new Message("A","all","something",false);
        String expectedText = "A says: something";

        assertEquals(expectedText, MessageFormatter.formatMessage(msg));
    }
    
    @Test
    void publicMessageToUser(){
        Message msg = new Message("A","B","something",false);
        String expectedText = "A says to B: something";

        assertEquals(expectedText, MessageFormatter.formatMessage(msg));
    }

    @Test
    void privateMessage(){
        Message msg = new Message("A","B","something",true);
        String expectedText = "A says privately to B: something";

        assertEquals(expectedText, MessageFormatter.formatMessage(msg));
    }

    @Test
    void serverMessage(){
        Message msg = new Message("server","B","something",true);
        String expectedText = "*** something";

        assertEquals(expectedText, MessageFormatter.formatMessage(msg));
    }
}
