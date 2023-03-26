package io.rjdev.booster.util.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import io.rjdev.booster.util.aws.AwsUtil;

@Disabled("Disabled temporary")
public class AwsUtilTest {

    AwsUtil awsu;
    String bucket_name = "devel2";

    @BeforeAll
    public static void start() {
        System.out.println("=======Starting junit 5 tests========");
    }

    @BeforeEach
    public void setup(){
        awsu = AwsUtil.builder().build().awsClient();
    }

    @Test
    public void listAllBucketsTest(){
        awsu.listAllBuckets();
    }

    @Test
    public void listObjectsTest(){

        awsu.listAllObjects(bucket_name);

    }

    // @Test
    public void putObjectS3Test(){
        awsu.uploadToS3(bucket_name, "file-test.txt", "src/main/resources/file-test.txt");

    }

    // @Test
    public void downloadFromS3Test(){
        awsu.downloadFromS3(bucket_name, "file-test.txt", "src/main/resources/data/file-test-s3.txt");
    }


    public void deleteObjectS3Test(){
        awsu.deleteObjectS3(bucket_name, "file-test.txt");
    }
}

