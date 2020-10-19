package io.github.mathiasfk.server.validations;

public class NicknameValidator {
    private NicknameValidator(){}

    private static final int MIN_LEN = 3;
    private static final int MAX_LEN = 15;

    public static boolean validateNickname(String nickname){
        if(nickname.isBlank()) return false;
        if(nickname.length() < MIN_LEN) return false;
        if(nickname.length() > MAX_LEN) return false;
        if(nickname.startsWith("/")) return false;

        return true;
    }
}
