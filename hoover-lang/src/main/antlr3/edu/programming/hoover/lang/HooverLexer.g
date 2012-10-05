lexer grammar HooverLexer;

options {
    language   = Java;
    superClass = AbstractHooverLexer;
}

@header {
    package edu.programming.hoover.lang;
}

PROGRAM     : 'program' ;
END         : 'end' ;
LOOP        : 'loop' ;
WHILE       : 'while' ;
IF          : 'if' ;
THEN        : 'then' ;
CAN         : 'can' ;
MATCH       : 'match' ;
MOVE        : 'move' ;
DIR_LEFT    : 'left' ;
DIR_RIGHT   : 'right' ;
DIR_UP      : 'up' ;
DIR_DOWN    : 'down' ;
PUT         : 'put' ;
TAKE        : 'take' ;
EMPTY       : 'empty' ;
FULL        : 'full' ;
AND         : 'and' ;
OR          : 'or' ;
NOT         : 'not' ;
LEFT_PAR    : '(' ;
RIGHT_PAR   : ')' ;

END_IF      : END IF ;
END_LOOP    : END LOOP ;

fragment BIN_DIGIT
         : '0'
         | '1'
         ;
fragment OCT_DIGIT
         : BIN_DIGIT
         | '2'..'7'
         ;
fragment DEC_DIGIT
         : OCT_DIGIT
         | '8'
         | '9'
         ;
fragment HEX_DIGIT
         : DEC_DIGIT
         | 'a'..'f'
         | 'A'..'F'
         ;
fragment BIN_SUFFIX
         : 'b'
         | 'B'
         ;
fragment OCT_SUFFIX
         : 'c'
         | 'C'
         ;
fragment HEX_SUFFIX
         : 'h'
         | 'H'
         ;
fragment QUOTE
         : '\''
         ;
fragment LETTER
         : 'a'..'z'
         | 'A'..'Z'
         ;
fragment SYMBOL
         : '+'
         | '-'
         | '/'
         | '*'
         | '!'
         | '@'
         | '#'
         | '$'
         | '%'
         | '^'
         | '&'
         | '('
         | ')'
         | '['
         | ']'
         | '{'
         | '}'
         | '|'
         | '\\'
         | ';'
         | '"'
         | ':'
         ;

TERMINATOR  : ';' ;
WHITESPACE  : (' ' | '\t' | '\n' | '\r' | '\u000c')+ { $channel = HIDDEN; } ;
LITERAL     : QUOTE ( LETTER | WHITESPACE | DEC_DIGIT | SYMBOL )* QUOTE ;

INTEGER     : BIN_DIGIT+ BIN_SUFFIX
            | OCT_DIGIT+ OCT_SUFFIX
            | DEC_DIGIT+
            | HEX_DIGIT+ HEX_SUFFIX
            ;

