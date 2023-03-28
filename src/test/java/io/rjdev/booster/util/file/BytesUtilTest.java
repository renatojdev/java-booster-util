package io.rjdev.booster.util.file;

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BytesUtilTest {

    @BeforeAll
    public static void start() {
        System.out.println("=======Starting junit 5 tests========");
    }

    @Test
    public void test_saveBytesToFile(){

        String fname = "src/main/resources/data/f1.txt";
        assert(BytesUtil.saveBytesToFile(fname, "Hello!".getBytes()));

    }

    @Test
    public void test_loadsBytesFromFile(){

        try {
            String fname = "src/main/resources/data/f1.txt";
            assert(Arrays.equals("Hello!".getBytes() , BytesUtil.loadBytesFromFile(fname)));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
