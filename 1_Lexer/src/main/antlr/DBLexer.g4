lexer grammar DBLexer;

// ===== Schlüsselwörter =====
NACH        : 'nach';
NICHT       : 'nicht';
TAEGLICH    : 'täglich';
AUCH        : 'auch';
BIS         : 'bis';

// ===== Verkehrsmittel / Linien =====
MODE        : 'Bus' | 'RE' | 'ICE' | 'IC' | 'EC' | 'IRE' | 'S' | 'KAT' | 'RB';
HBF         : 'Hbf';

// ===== Wochentage (kurz) =====
WOCHENTAG   : 'Mo' | 'Di' | 'Mi' | 'Do' | 'Fr' | 'Sa' | 'So';

// ===== Monate (de / kurz + lang) =====
MONAT
 : 'Jan' | 'Januar'
 | 'Feb' | 'Februar'
 | 'Mär' | 'Mae' | 'Maer' | 'März' | 'Maerz'
 | 'Apr' | 'April'
 | 'Mai'
 | 'Jun' | 'Juni'
 | 'Jul' | 'Juli'
 | 'Aug' | 'August'
 | 'Sep' | 'Sept' | 'September'
 | 'Okt' | 'Oktober'
 | 'Nov' | 'November'
 | 'Dez' | 'Dezember'
 ;

// ===== Zeit / Datum / Zahlen =====
TIME        : DIGIT DIGIT? ':' DIGIT DIGIT;            // 9:45, 09:45, 10:00
DAY_DOT     : DIGIT? DIGIT '.';                        // 1., 9., 30., 11.
YEAR        : DIGIT DIGIT DIGIT DIGIT;                 // 2024
INT         : DIGIT+;                                  // 700, 1, 9, 4720

// ===== Interpunktion & Zeichen =====
KOMMA       : ',';
SEMI        : ';';
DASH        : [\-–—];                                  // - oder Gedankenstrich
SLASH       : '/';
LPAREN      : '(';
RPAREN      : ')';

// ===== Stations-/Orts-/Freitextwörter =====
// Einzelwörter (ohne Leerzeichen/Komma/Semikolon/Strich/Klammern)
WORD
 : LETTER (LETTER | DIGIT | [._'’\-])*
 ;

// ===== Fragmente =====
fragment LETTER : [A-Za-zÄÖÜäöüß];
fragment DIGIT  : [0-9];

// ===== Whitespace ignorieren =====
WS : [ \t\r\n]+ -> channel(HIDDEN);
