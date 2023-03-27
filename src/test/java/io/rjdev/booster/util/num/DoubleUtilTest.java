package io.rjdev.booster.util.num;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class DoubleUtilTest {

    @BeforeAll
    public static void start() {
        System.out.println("=======Starting junit 5 tests========");
    }

    @Test
    public void test_doubleToString(){

        assert("110,58".equals(DoubleUtil.doubleToString(new Double(110.5789))));
        assert("11,10".equals(DoubleUtil.doubleToString(new Double(11.1))));
        assert("2340,00".equals(DoubleUtil.doubleToString(new Double(2340))));
        assert("123,40".equals(DoubleUtil.doubleToString(new Double(123.40))));
        assert("120530,40".equals(DoubleUtil.doubleToString(new Double(120530.40))));
    }

    @Test
    public void test_moveIdentificadorDecimal(){
        assert(1.23 == DoubleUtil.moveIdentificadorDecimal(12333.45, (short) 4));
        assert(12.33 == DoubleUtil.moveIdentificadorDecimal(12333.45, (short) 3));
        assert(123.33 == DoubleUtil.moveIdentificadorDecimal(12333.45, (short) 2));
        assert(1233.35 == DoubleUtil.moveIdentificadorDecimal(12333.45, (short) 1));
    }

}
