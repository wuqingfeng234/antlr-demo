package com.git.wuqf.expression.bigdecimal;

import com.git.wuqf.expression.ExpressionBaseVisitor;
import com.git.wuqf.expression.ExpressionLexer;
import com.git.wuqf.expression.ExpressionParser;
import org.antlr.v4.runtime.Token;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

public class BigDecimalCalculationVisitor  extends ExpressionBaseVisitor<BigDecimal> {
    private static final BigDecimal HUNDRED = BigDecimal.valueOf(100);

    private static final MathContext MATH_CONTEXT = MathContext.DECIMAL128;

    @Override
    public BigDecimal visitCalculationBlock(ExpressionParser.CalculationBlockContext ctx) {
        BigDecimal calcResult = null;
        for (ExpressionParser.ExprContext expr : ctx.expr()) {
            calcResult = visit(expr);
        }
        return calcResult;
    }

    @Override
    public BigDecimal visitExpressionTimesOrDivide(ExpressionParser.ExpressionTimesOrDivideContext ctx) {
        BigDecimal left = visit(ctx.expr(0));
        BigDecimal right = visit(ctx.expr(1));
        switch (ctx.op.getType()) {
            case ExpressionLexer.TIMES: return left.multiply(right, MATH_CONTEXT);
            case ExpressionLexer.DIVIDE: return left.divide(right, MATH_CONTEXT);
            default: throw new RuntimeException("unsupported operator type");
        }
    }

    @Override
    public BigDecimal visitExpressionPlusOrMinus(ExpressionParser.ExpressionPlusOrMinusContext ctx) {
        BigDecimal left = visit(ctx.expr(0));
        BigDecimal right = visit(ctx.expr(1));
        switch (ctx.op.getType()){
            case ExpressionLexer.PLUS:return left.add(right,MATH_CONTEXT);
            case ExpressionLexer.MINUS:return left.subtract(right,MATH_CONTEXT);
            default: throw new RuntimeException("unsupported operator type");
        }
    }

    @Override
    public BigDecimal visitExpressionWithBr(ExpressionParser.ExpressionWithBrContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public BigDecimal visitExpressionNumeric(ExpressionParser.ExpressionNumericContext ctx) {
        BigDecimal numeric = numberOrPercent(ctx.num);
        if (Objects.nonNull(ctx.sign) && ctx.sign.getType() == ExpressionLexer.MINUS) {
            return numeric.negate();
        }
        return numeric;
    }

    private BigDecimal numberOrPercent(Token num) {
        String numberStr = num.getText();
        switch (num.getType()) {
            case ExpressionLexer.NUMBER: return decimal(numberStr);
            case ExpressionLexer.PERCENT_NUMBER: return decimal(numberStr.substring(0, numberStr.length() - 1).trim()).divide(HUNDRED, MATH_CONTEXT);
            default: throw new RuntimeException("unsupported number type");
        }
    }

    private BigDecimal decimal(String decimalStr) {
        return new BigDecimal(decimalStr);
    }
}
