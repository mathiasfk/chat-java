package io.github.mathiasfk.server.validations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NicknameValidatorTest {

    @Test
    void validName(){
        assertTrue(NicknameValidator.validateNickname("john"));
        assertTrue(NicknameValidator.validateNickname("john wick"));
    }

    @Test
    void unallowedLength(){
        assertFalse(NicknameValidator.validateNickname("me"));
        assertFalse(NicknameValidator.validateNickname("loooooooooooooooooooooooooooooong naaaaaaaaaaame"));
    }

    @Test
    void emptyName(){
        assertFalse(NicknameValidator.validateNickname(""));
        assertFalse(NicknameValidator.validateNickname(" "));
    }

    @Test
    void unallowedCharacters(){
        assertFalse(NicknameValidator.validateNickname("/exit"));
    }
}
