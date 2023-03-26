package io.rjdev.booster.util.string;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit test for String Util class.
 */
public class StringUtilTest{

    /**
     * Rigourous Test :-)
     */
    @Test
    public void testApp(){
        assertAll("str",
            () -> assertEquals(StringUtil.removeCharFromString('a', "textosemoa"), "textosemo"),
            () -> assertEquals(StringUtil.removeCharFromString('t', "textosemoa"), "exosemoa")
        );
    }

    @Test
    public void testApp2(){
        assertNotEquals(StringUtil.removeCharFromString( 'a', "textosemoa"), "textosemoa");
    }

    @Test
    public void testApp3(){
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
        StringUtil.removeCharFromString( 'a',  null));
        assertEquals("No arguments provided", exception.getMessage());
        // fail("Fail expected");
    }

}

