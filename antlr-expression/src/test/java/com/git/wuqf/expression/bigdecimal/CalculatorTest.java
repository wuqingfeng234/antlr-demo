package com.git.wuqf.expression.bigdecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    private final Calculator calculator = new Calculator();

    @DisplayName("Test Calculator")
    @ParameterizedTest
    @CsvSource({
            "1 + 2, 3",
            "3 - 2, 1",
            "2 * 3, 6",
            "6 / 3, 2",
            "6 / (1 + 2) , 2",
            "50%, 0.5",
            "100 * 30%, 30.0"
    })
    void testCalculation(String expression, String expected) {
        assertEquals(calculator.execute(expression).toPlainString(), expected);
    }
}
