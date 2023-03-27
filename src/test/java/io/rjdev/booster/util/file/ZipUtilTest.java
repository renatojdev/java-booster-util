package io.rjdev.booster.util.file;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ZipUtilTest {


    @BeforeAll
    public static void start() {
        System.out.println("=======Starting junit 5 tests========");
    }


    @Test
    public void test_zip(){
        String[] filesToZip = {"src/main/resources/data/f1.txt"};
        assert(ZipUtil.zip("src/main/resources/f1.zip", filesToZip));
    }


    @Test
    public void test_unzip(){
        assert(ZipUtil.unzip("src/main/resources/f1.zip", "src/main/resources/data"));
    }

}
