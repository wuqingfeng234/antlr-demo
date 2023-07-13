package com.git.wuqf.antlr.sql.base;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SqlGrammaParseTest {
    public static void main(String[] args) {
        String sql = "SELECT name FROM users";
        CharStream stream = CharStreams.fromString(sql);
        SqlBaseLexer lexer1 = new SqlBaseLexer(stream);
        CommonTokenStream tokens1 = new CommonTokenStream(lexer1);
        SqlBaseParser parser1 = new SqlBaseParser(tokens1);
        parser1.getInterpreter().setPredictionMode(PredictionMode.SLL);
        parser1.removeErrorListeners(); // 移除所有错误的监听
        // parser1.addErrorListener();

        ParseTree tree = null;
        try {
            tree = parser1.singleStatement(); // STAGE 1
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SimpleSqlTreeVisitor simpleSqlTreeVisitor = new SimpleSqlTreeVisitor();
        if (tree != null) {
            SelectSql selectSql = simpleSqlTreeVisitor.visit(tree);
            System.out.println(selectSql);
            if (!selectSql.isQuery()) {
                throw new RuntimeException("当前执行的sql不是查询！");
            }
            System.out.print(selectSql.startToken);
            System.out.print(" " + selectSql.fields.stream().collect(Collectors.joining(",")));


            if (selectSql.from) {
                System.out.print(" FROM");
                System.out.print(" " + selectSql.tableName);
            }
            System.out.println();
        }
    }

    private static class SelectSql {
        private String startToken;
        private boolean from;
        private List<String> fields;
        private String tableName;

        public boolean isQuery() {
            return "SELECT".equalsIgnoreCase(startToken);
        }
    }

    private static class SimpleSqlTreeVisitor implements ParseTreeVisitor<SelectSql> {
        SelectSql selectSql;

        SimpleSqlTreeVisitor() {
            selectSql = new SelectSql();
            selectSql.fields = new ArrayList<>();
        }

        @Override
        public SelectSql visit(ParseTree tree) {
            int childCount = tree.getChildCount();
            for (int i = 0; i < childCount; i++) {
                ParseTree child = tree.getChild(i);
                if (child instanceof SqlBaseParser.StatementContext) {
                    SqlBaseParser.StatementContext statementContext = (SqlBaseParser.StatementContext) child;
                    System.out.println("statementContext");
                    handlerWithStatement(statementContext);
                }
            }
            return selectSql;
        }

        private void handlerWithStatement(SqlBaseParser.StatementContext statementContext) {
            int childCount = statementContext.getChildCount(); // 必定以select开头
            int i = 0;
            for (; i < childCount; i++) {
                ParseTree child = statementContext.getChild(i);
                if (child instanceof SqlBaseParser.SelectElementsContext) {
                    System.out.println("SelectElementsContext");
                    selectSql.fields.add(child.getText());// 这里应该是有多个
                } else if (child instanceof SqlBaseParser.FromClauseContext) {
                    System.out.println("FromClauseContext");
                    selectSql.tableName = child.getChild(1).getText();
                    selectSql.from = true;
                } else if (child instanceof TerminalNode) {
                    selectSql.startToken = child.getText(); // 这个就是SELECT
                    // System.out.println("TerminalNode==>" + terminalNode);
                }

            }
        }

        @Override
        public SelectSql visitChildren(RuleNode node) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public SelectSql visitTerminal(TerminalNode node) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public SelectSql visitErrorNode(ErrorNode node) {
            // TODO Auto-generated method stub
            return null;
        }

    }
}

