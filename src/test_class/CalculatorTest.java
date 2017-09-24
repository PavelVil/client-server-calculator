package test_class;


import com.test.calc.Calculator;
import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {

    @Test
    public void easyExpressionTest() {
        double easyExpression = Double.parseDouble(Calculator.calculate("2+2"));
        Assert.assertEquals(4.0d, easyExpression, 0);
    }

    @Test
    public void middleExpressionTest() {
        double middleExpression = Double.parseDouble(Calculator.calculate("18*(20-12)+40/2*(2+6)"));
        Assert.assertEquals(304, middleExpression, 0);
    }

    @Test
    public void isNaNTest() {
        double notNumberExpression = Double.parseDouble(Calculator.calculate("1/0"));
        Assert.assertEquals(Double.NaN, notNumberExpression, 0);
    }

    @Test(expected = NumberFormatException.class)
    public void numberFormatExceptionTest () {
        double middleExpression = Double.parseDouble(Calculator.calculate("It's a string!"));
        Assert.assertEquals(4, middleExpression,0);
    }

}
