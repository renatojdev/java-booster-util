package io.rjdev.booster.util.aws;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import io.rjdev.booster.util.Resource;

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
        objects = awsu.listObjects(null, "deployments");
        assert(!objects.isEmpty());
        objects = awsu.listObjects(null, null);
        assert(objects == null);
    }

    @Test
    public void putObjectS3Test(){
        awsu.uploadToS3(bucket_name, "file-test.txt", "src/main/resources/file-test.txt");
        awsu.uploadToS3(null, "file-test.txt", "src/main/resources/file-test.txt");
    }

    @Test
    public void downloadFromS3Test(){
        awsu.downloadFromS3(bucket_name, "file-test.txt", "src/main/resources/data/file-test-s3.txt");
        awsu.downloadFromS3(null, "file-test.txt", "src/main/resources/data/file-test-s3.txt");
    }


    public void deleteObjectS3Test(){
        awsu.deleteObjectS3(bucket_name, "file-test.txt");
        awsu.deleteObjectS3(null, "file-test.txt");
    }
}

