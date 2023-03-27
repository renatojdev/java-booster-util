package io.rjdev.booster.util.file;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
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

        try {
            String fname = "src/main/resources/data/f1.txt";
            BytesUtil.saveBytesToFile(fname, "Hello!".getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
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
