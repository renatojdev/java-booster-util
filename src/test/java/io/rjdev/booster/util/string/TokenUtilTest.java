package io.rjdev.booster.util.string;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class TokenUtilTest {

    String[] arr = {"I", "like", "coffee"};
    String[] arr2 = {"Hello", " world!"};

     /**
     * Rigourous Test :-)
     */
    @Test
    public void test_separa(){
        assertAll("str",
            () -> assertTrue(Arrays.equals(arr, Token.separate("I like coffee", " "))),
            () -> assertTrue(Arrays.equals(arr2, Token.separate("Hello, world!", ",")))
        );
    }

}
