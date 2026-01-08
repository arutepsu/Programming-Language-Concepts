lexer grammar MiniJSONLexer;

TRUE  : 'true';
FALSE : 'false';
NULL  : 'null';

LBRACE : '{';
RBRACE : '}';
LBRACK : '[';
RBRACK : ']';
COLON  : ':';
COMMA  : ',';

STRING
    : '"' ( ~["\\] | '\\' . )* '"'
    ;

NUMBER
    : '-'? INT ( '.' [0-9]+ )?
    ;

fragment INT : '0' | [1-9] [0-9]*;

WS
    : [ \t\r\n]+ -> skip
    ;
