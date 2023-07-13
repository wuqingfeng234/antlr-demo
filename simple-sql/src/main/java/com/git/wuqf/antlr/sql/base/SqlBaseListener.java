// Generated from D:/code/antlr-demo/antlr-sql/src/main/resources\SqlBase.g4 by ANTLR 4.12.0
 package com.git.wuqf.antlr.sql.base;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SqlBaseParser}.
 */
public interface SqlBaseListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SqlBaseParser#singleStatement}.
	 * @param ctx the parse tree
	 */
	void enterSingleStatement(SqlBaseParser.SingleStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlBaseParser#singleStatement}.
	 * @param ctx the parse tree
	 */
	void exitSingleStatement(SqlBaseParser.SingleStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlBaseParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(SqlBaseParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlBaseParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(SqlBaseParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlBaseParser#fromClause}.
	 * @param ctx the parse tree
	 */
	void enterFromClause(SqlBaseParser.FromClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlBaseParser#fromClause}.
	 * @param ctx the parse tree
	 */
	void exitFromClause(SqlBaseParser.FromClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlBaseParser#selectElements}.
	 * @param ctx the parse tree
	 */
	void enterSelectElements(SqlBaseParser.SelectElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlBaseParser#selectElements}.
	 * @param ctx the parse tree
	 */
	void exitSelectElements(SqlBaseParser.SelectElementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlBaseParser#tableName}.
	 * @param ctx the parse tree
	 */
	void enterTableName(SqlBaseParser.TableNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlBaseParser#tableName}.
	 * @param ctx the parse tree
	 */
	void exitTableName(SqlBaseParser.TableNameContext ctx);
}