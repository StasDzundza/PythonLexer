package com.university.lexer.token;

public enum TokenName {
    WHITESPACE, // \t, space, ......
    NEW_LINE,
    INDENT,
    DEDENT,
    COMMENT,//#
    OPERATOR, //+,-,*,/,**,&,|,^
    COMPARISON_OPERATOR, //<,<=,>,>=,==,!=
    ASSIGNMENT_OPERATOR,//=,+=,-=,%=,*=,/=,&=,|=,^=
    KEYWORD, //import,as,class,def,pass,if,elif,else,try,except,throw,for,while,in,is,await,None,raise,True,False,return
    // and,or,lambda,break,continue,from,assert,with,not,async,yield,global,del
    DATA_TYPE, //int,float,complex,str,bool
    SEPARATOR, //, ; :
    BRACKET, // (, ), [, ], {, }
    DOT, // .
    ID,
    NUMBER,
    STRING,
    ERROR_TOKEN
}
