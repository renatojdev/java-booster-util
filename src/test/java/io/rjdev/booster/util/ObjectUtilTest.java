package io.rjdev.booster.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ObjectUtilTest {

    @BeforeAll
    public static void start() {
        System.out.println("=======Starting junit 5 tests========");
    }

    @Test
    public void test_isEqual_notEqual(){
        assertTrue( ObjectUtil.isEqual(String.valueOf("a"), new String("a") ));
        assertFalse( ObjectUtil.isEqual(String.valueOf("a"), new String("") ));
        assertTrue( ObjectUtil.notEqual(String.valueOf("a"), new String("") ));
        assertFalse( ObjectUtil.notEqual(String.valueOf("a"), new String("a") ));
    }

}
