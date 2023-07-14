package com.git.wuqf.antlr.mysql;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Set;

public class GetTableNamesListenerTest {

    @Test
    void testGetTableName() {
        String sql = "SELECT t1.column1,t1.column2,t1.column3,t2.xy from tableC t1 left join tableA t2 on t1.id=t2.oid where t1.column1 = 1 and t2.yy=6";
        System.out.println(sql);
        MySqlLexer lexer = new MySqlLexer(CharStreams.fromString(sql.toUpperCase()));
        MySqlParser parser = new MySqlParser(new CommonTokenStream(lexer));
        //定义GetTableNamesListener
        GetTableNamesListener listener = new GetTableNamesListener();
        ParseTreeWalker.DEFAULT.walk(listener, parser.sqlStatements());
        Set<String> tableNameSet = listener.getTableNameSet();
        for (String tableName : tableNameSet) {
            System.out.println(tableName);
        }
    }

}
