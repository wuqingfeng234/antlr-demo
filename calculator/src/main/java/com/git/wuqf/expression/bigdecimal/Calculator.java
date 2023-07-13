package com.git.wuqf.expression.bigdecimal;

import com.git.wuqf.expression.ExpressionLexer;
import com.git.wuqf.expression.ExpressionParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.math.BigDecimal;

public class Calculator {
    public BigDecimal execute(String expression) {
        CharStream cs = CharStreams.fromString(expression);
        ExpressionLexer lexer = new ExpressionLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExpressionParser parser = new ExpressionParser(tokens);
        ExpressionParser.CalcContext context = parser.calc();
        BigDecimalCalculationVisitor visitor = new BigDecimalCalculationVisitor();
        return visitor.visit(context);
    }
}
