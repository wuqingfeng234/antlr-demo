grammar SqlBase;

@header { package com.git.wuqf.antlr.sql.base; }

singleStatement : statement EOF ;

statement : 'SELECT' selectElements fromClause ;

fromClause : 'FROM' tableName ;

selectElements : ID ;

tableName : ID ;

ID : [a-zA-Z]+ ;

WS : [ \r\n\t]+ -> skip ;
