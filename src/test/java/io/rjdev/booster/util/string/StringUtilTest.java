package io.rjdev.booster.util.string;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit test for String Util class.
 */
public class StringUtilTest{

    /**
     * Rigourous Test :-)
     */
    @Test
    public void test_removeCharFromString(){
        assertAll("str",
            () -> assertEquals(StringUtil.removeCharFromString('a', "textosemoa"), "textosemo"),
            () -> assertEquals(StringUtil.removeCharFromString('t', "textosemoa"), "exosemoa")
        );
    }

    @Test
    public void test_removeCharFromStringNot(){
        assertNotEquals(StringUtil.removeCharFromString( 'a', "textosemoa"), "textosemoa");
    }

    @Test
    public void test_removeCharFromStringExcept(){
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
        StringUtil.removeCharFromString( 'a',  null));
        assertEquals("No arguments provided", exception.getMessage());
        // fail("Fail expected");
    }

    @Test
    public void test_removeLineBreak(){
        assertAll("str",
            () -> assertTrue("Texto sem quebra de linha".equals(StringUtil.removeLineBreak("Texto\nsem\nquebra\nde\nlinha"))),
            () -> assertTrue("Texto sem quebra de linha".equals(StringUtil.removeLineBreak("Texto\r\nsem\r\nquebra\r\nde\r\nlinha")))
        );
    }

    @Test
    public void test_isAlphanumeric(){
        assertAll("str",
            () -> assertTrue(StringUtil.isAlphanumeric("abc123")),
            () -> assertTrue(StringUtil.isAlphanumeric(" ")),
            () -> assertTrue(StringUtil.isAlphanumeric("911")),
            () -> assertFalse(StringUtil.isAlphanumeric("#abc123")),
            () -> assertFalse(StringUtil.isAlphanumeric("#&*#@!@")),
            () -> assertFalse(StringUtil.isAlphanumeric(null))
        );
    }

    @Test
    public void test_numericCharsQty(){
        assertAll("str",
            () -> assertTrue(3 == StringUtil.numericCharsQty("abc123")),
            () -> assertTrue(0 == StringUtil.numericCharsQty(" ")),
            () -> assertTrue(0 == StringUtil.numericCharsQty(null))
        );
    }

    @Test
    public void test_charactersAlphabeticQty(){
        assertAll("str",
            () -> assertTrue(3 == StringUtil.charactersAlphabeticQty("abc123")),
            () -> assertTrue(0 == StringUtil.charactersAlphabeticQty(" ")),
            () -> assertTrue(1 == StringUtil.charactersAlphabeticQty("1A")),
            () -> assertTrue(0 == StringUtil.charactersAlphabeticQty(null))
        );
    }

    @Test
    public void test_isNumMinimCharsRequired(){
        assertAll("str",
            () -> assertTrue(StringUtil.isNumMinimCharsRequired("abc123", 3, 2, 2)),
            () -> assertFalse(StringUtil.isNumMinimCharsRequired("abc123", 3, 5, 3)),
            () -> assertTrue(StringUtil.isNumMinimCharsRequired("12 bc123", 3, 5, 3)),
            () -> assertFalse(StringUtil.isNumMinimCharsRequired("abc123", 8, 5, 3)),
            () -> assertTrue(StringUtil.isNumMinimCharsRequired("112bc12c", 8, 5, 3))
        );
    }

    @Test
    public void test_removeDuplicateBlankSpace(){
        assert(" Lorem ipsum lorem ipsum".equals(StringUtil.removeDuplicateBlankSpace("  Lorem ipsum lorem  ipsum")));
        assert(null == StringUtil.removeDuplicateBlankSpace(null));
    }

    @Test
    public void test_getStringBreakingLine(){
        assert("Lorem ipsum dolor sit amet,<br/>consectetur adipiscing<br/>elit. Sed non risus. Suspendisse<br/>lectus tortor, dignissim<br/>sit amet, adipiscing nec,<br/>ultricies sed, dolor.".equals(StringUtil.getStringBreakingLine("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor.", 22)));
    }

    @Test
    public void test_replace(){
        assert("Lorem ipsum. Sed non risus".equals(StringUtil.replace("Lorem ipsum. Sed non risus", "ipsum")));
    }

    @Test
    public void test_getBreakLineHtml(){
        assert("Lorem ipsum.<br/>Sed non risus.".equals(StringUtil.getBreakLineHtml("Lorem ipsum.\nSed non risus.")));
        assert(null == StringUtil.getBreakLineHtml(null));
    }

    @Test
    public void test_removeChar(){
        assert("Lrem ipsum. Sed nn risus.".equals(StringUtil.removeChar("Lorem ipsum. Sed non risus.", 'o')));
        assert("Lorem ipsum. Sed non risus.".equals(StringUtil.removeChar("Lorem ipsum. Sed non risus.", null)));
        assert( null == StringUtil.removeChar(null, 'c'));
    }

    @Test
    public void testInNotBlank(){
        assert(!StringUtil.isNotBlank(null)); // false
        assert(!StringUtil.isNotBlank("")); // false
        assert(!StringUtil.isNotBlank(" ")); // false
        assert(StringUtil.isNotBlank("bob"));// true
        assert(StringUtil.isNotBlank(" bob "));// true
    }

}

