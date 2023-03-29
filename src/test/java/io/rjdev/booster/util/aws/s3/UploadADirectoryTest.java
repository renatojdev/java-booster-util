package io.rjdev.booster.util.aws.s3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.rjdev.booster.util.Resource;
import io.rjdev.booster.util.aws.s3.tm.UploadADirectory;

public class UploadADirectoryTest {
    static UploadADirectory up;
    static String access_key;
    static String secret_access_key;
    static String bucket_name;
    static String sourceDirectory;

    @BeforeAll
    public static void start() {
        System.out.println("=======Starting junit 5 tests========");
        access_key = Resource.get("access_key");
        secret_access_key = Resource.get("secret_access_key");
        bucket_name = Resource.get("default_bucket_name");
        sourceDirectory = Resource.get("default_upload_dir");
    }

    @BeforeEach
    public void setUp(){
        up = new UploadADirectory(access_key, secret_access_key);
    }

    @AfterEach
    public void end(){
        up=null;
    }

    @Test
    public void test_uploadADir(){
        assert(0 == up.upload());
        assert(up.cleanUp());
    }

    @Test
    public void test_uploadADirSourceDir(){
        assert(0 == up.upload(sourceDirectory));
        assert(up.cleanUp());
    }

    @Test
    public void test_uploadADirBucketName(){
        assert(0 == up.upload(null, bucket_name));
    }

}
