package io.rjdev.booster.util.util;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import io.rjdev.booster.util.Resource;
import io.rjdev.booster.util.aws.AwsUtil;

@Disabled("Disabled temporary")
public class AwsUtilTest {

    AwsUtil awsu;
    static String bucket_name;

    @BeforeAll
    public static void start() {
        System.out.println("=======Starting junit 5 tests========");
        Resource resource = Resource.getInstance();
        bucket_name = resource.get("default_bucket_name");
    }

    @BeforeEach
    public void setup(){
        awsu = AwsUtil.builder().build().awsClient();
    }

    // @Test
    public void listAllBucketsTest(){
        awsu.listAllBuckets();
    }

    @Test
    public void listAllObjectsTest(){
        awsu.listAllObjects(bucket_name);

    }

    @Test
    public void listObjectsTest(){
        List<String> objects = awsu.listObjects(bucket_name, "deployments");
        assert(!objects.isEmpty());

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

