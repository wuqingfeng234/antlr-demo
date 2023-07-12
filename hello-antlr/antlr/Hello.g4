// Define a grammar called Hello
grammar Hello; // 1. grammer name
@header { package com.git.wuqf.antlr; } // 2. java package
r  : 'hello' ID ;         // 3. match keyword hello followed by an identifier
ID : [a-z]+ ;             // match lower-case identifiers
WS : [ \t\r\n]+ -> skip ; // 4. skip spaces, tabs, newline