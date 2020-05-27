package com.university.lexer.token;

public class Token {
    private TokenName tokenName;
    private String value;
    private Location begin,end;

    public Token(TokenName tokenName, String value, Location begin, Location end) {
        this.tokenName = tokenName;
        this.value = value;
        this.begin = begin;
        this.end = end;
    }

    @Override
    public String toString() {
        return "{" + tokenName.toString() + " | " + value + "} starts at:" + begin.toString() + " finishes at:" + end.toString();
    }
}
