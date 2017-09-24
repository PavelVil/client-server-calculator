package com.test.calc;

import org.mariuszgromada.math.mxparser.Expression;
/**
 * This class is responsible for handling expressions that the user will send.
 * This class uses the library mXparser.
 * For all information about library please link to http://mathparser.org/
 * */
public final class Calculator {

    /**
     * @param expression - it is the expression to be parsed and calculated
     * @return a calculated value
     * @exception NumberFormatException can be generated
     * */
    public static String calculate(String expression) {
        Expression exp = new Expression(expression);
        if (!exp.checkSyntax()) {
            return exp.getErrorMessage();
        } else {
            String expressionLine = Double.toString(exp.calculate());
            return expressionLine;
        }
    }

}
