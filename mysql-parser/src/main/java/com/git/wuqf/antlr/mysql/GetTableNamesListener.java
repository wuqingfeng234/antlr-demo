package com.git.wuqf.antlr.mysql;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GetTableNamesListener extends MySqlParserBaseListener {

    public static void main(String[] args) {
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

    private final Set<String> tableNameSet = new HashSet<>();

    @Override
    public void enterTableSources(MySqlParser.TableSourcesContext ctx) {
        List<MySqlParser.TableSourceContext> tableSourceContexts = ctx.getRuleContexts(MySqlParser.TableSourceContext.class);
        for (MySqlParser.TableSourceContext tableSource : tableSourceContexts) {
            //通过tableSourceItems获取表名
            getTableNameByTableSourceItems(tableSource.getRuleContexts(MySqlParser.TableSourceItemContext.class));
            //获取join部分
            List<MySqlParser.OuterJoinContext> joinContexts = tableSource.getRuleContexts(MySqlParser.OuterJoinContext.class);
            for (MySqlParser.OuterJoinContext joinContext : joinContexts) {
                List<MySqlParser.TableSourceItemContext> tableSourceItemContexts = joinContext.getRuleContexts(MySqlParser.TableSourceItemContext.class);
                getTableNameByTableSourceItems(tableSourceItemContexts);
            }
        }
    }

    private void getTableNameByTableSourceItems(List<MySqlParser.TableSourceItemContext> tableSourceItems) {
        for (MySqlParser.TableSourceItemContext tableSourceItem : tableSourceItems) {
            List<MySqlParser.TableNameContext> tableNameContexts = tableSourceItem.getRuleContexts(MySqlParser.TableNameContext.class);
            for (MySqlParser.TableNameContext tableNameContext : tableNameContexts) {
                tableNameSet.add(tableNameContext.getText());
            }
        }
    }

    public Set<String> getTableNameSet() {
        return tableNameSet;
    }
}
