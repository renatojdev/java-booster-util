package io.rjdev.booster.util.aws.s3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.rjdev.booster.util.Resource;
import io.rjdev.booster.util.aws.s3.tm.UploadADirectory;

public class UploadADirectoryTest {

    static String access_key;
    static String secret_access_key;

    @BeforeAll
    public static void start() {
        System.out.println("=======Starting junit 5 tests========");
        access_key = Resource.get("access_key");
        secret_access_key = Resource.get("secret_access_key");
    }

    @Test
    public void test_uploadADir(){
        UploadADirectory up = new UploadADirectory(access_key, secret_access_key);
        assert(0 == up.upload());
    }


}
