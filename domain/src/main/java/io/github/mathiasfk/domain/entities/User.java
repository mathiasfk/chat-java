package io.github.mathiasfk.domain.entities;

public class User {
    private String id;
    private String nickname;

    public User(String nickname){
        this.nickname = nickname;
    }
}