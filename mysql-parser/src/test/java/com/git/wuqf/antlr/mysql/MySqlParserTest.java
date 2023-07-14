package com.git.wuqf.antlr.mysql;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.Trees;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class MySqlParserTest {
    @Test
    void testPrintSyntaxTree() {
//        String sql = "SELECT t1.column1,t1.column2,t1.column3,t2.xy from tableC t1 left join tableA t2 on t1.id=t2.oid where t1.column1 = 1 and t2.yy=6";
        String sql = "SELECT * from user";
        //词法分析器
        MySqlLexer mySqlLexer = new MySqlLexer(CharStreams.fromString(sql.toUpperCase()));
        //词法符号的缓冲区,用于存储词法分析器生成的词法符号
        CommonTokenStream commonTokenStream = new CommonTokenStream(mySqlLexer);
        //新建一个语法分析器，处理词法符号缓冲区内容
        MySqlParser mySqlParser = new MySqlParser(commonTokenStream);
        //获取出selectStatement
        ParseTree tree = mySqlParser.selectStatement();
        System.out.println(printSyntaxTree(mySqlParser, tree));
    }

    private String printSyntaxTree(Parser parser, ParseTree root) {
        StringBuilder buf = new StringBuilder();
        recursive(root, buf, 0, Arrays.asList(parser.getRuleNames()));
        return buf.toString();
    }

    private void recursive(ParseTree aRoot, StringBuilder buf, int offset, List<String> ruleNames) {
        for (int i = 0; i < offset; i++) {
            buf.append("  ");
        }
        buf.append(Trees.getNodeText(aRoot, ruleNames)).append("\n");
        if (aRoot instanceof ParserRuleContext) {
            ParserRuleContext prc = (ParserRuleContext) aRoot;
            if (prc.children != null) {
                for (ParseTree child : prc.children) {
                    recursive(child, buf, offset + 1, ruleNames);
                }
            }
        }
    }
}
