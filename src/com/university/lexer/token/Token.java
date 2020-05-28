package com.university.lexer.token;

public class Token {
    private TokenName tokenName;
    private String value;
    private Location begin;

    public Token(TokenName tokenName, String value, Location begin) {
        this.tokenName = tokenName;
        this.value = value;
        this.begin = begin;
    }

    @Override
    public String toString() {
        if(value != null) {
            return "{" + tokenName.toString() + " | " + value + "} starts at : " + begin.toString();
        }else{
            return "{" + tokenName.toString() + "} starts at : " + begin.toString();
        }
    }
}
