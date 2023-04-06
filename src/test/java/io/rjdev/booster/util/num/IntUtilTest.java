package io.rjdev.booster.util.num;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class IntUtilTest {

    @BeforeAll
    public static void start() {
        System.out.println("=======Starting junit 5 tests========");
    }

    @Test
    public void test_randomNumber(){
        assert String.valueOf(IntUtil.randomNumber((byte) 6)).length() == 6;
    }


}
