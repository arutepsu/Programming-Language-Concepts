parser grammar MiniJSONParser;

options {
    tokenVocab = MiniJSONLexer;
}

json
    : value EOF
    ;

value
    : STRING
    | NUMBER
    | object
    | array
    | TRUE
    | FALSE
    | NULL
    ;

object
    : LBRACE pair (COMMA pair)* RBRACE // { k:v, k:v... }
    | LBRACE RBRACE                    // {}
    ;

pair
    : STRING COLON value
    ;

array
    : LBRACK value (COMMA value)* RBRACK
    | LBRACK RBRACK
    ;
