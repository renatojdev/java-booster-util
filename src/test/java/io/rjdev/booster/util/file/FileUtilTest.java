package io.rjdev.booster.util.file;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
public class FileUtilTest {

    @BeforeAll
    public static void start() {
        System.out.println("=======Starting junit 5 tests========");
    }

    @Test
    @Order(1)
    public void test_fileToString(){

        try {
            String fname = "src/main/resources/data/f1.txt";
            // FileUtil fu = FileUtil.builder().fileNameString(fname).build();
            assert("Hello!".equals(FileUtil.fileToString(fname)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    public void test_mkdir(){

        try {
            // String fname = "src/main/resources/data/f1.txt";
            FileUtil fu = new FileUtil();
            assert(!fu.mkdir());
            fu.setPath("src/main/resources/data2");
            assert(!fu.mkdir());

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    @Test
    @Order(3)
    public void test_fileSave(){

        try {
            FileUtil fu = new FileUtil();
            fu.setFileNameString("f1.txt");
            fu.setPath("src/main/resources/data2");
            assert(fu.fileSave("Hello there!".getBytes()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(4)
    public void test_loadBytesFromFile(){

        try {
            FileUtil fu = new FileUtil();
            fu.setFileNameString("f1.txt");
            fu.setPath("src/main/resources/data2");
            assert(Arrays.equals("Hello there!".getBytes(), fu.loadBytesFromFile()));

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    @Order(5)
    public void test_dirFilesLength(){

        try {
            FileUtil fu = new FileUtil();
            fu.setPath("src/main/resources/data2");
            assert(fu.dirFilesLength() == 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(6)
    public void test_deleteDir(){

        try {
            FileUtil fu = new FileUtil();
            fu.setFileNameString("f1.txt");
            fu.setPath("src/main/resources/data2");
            assert(fu.delete(false));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
