package io.rjdev.booster.util.aws;

import java.util.List;

import io.rjdev.booster.util.Resource;
import io.rjdev.booster.util.aws.s3.DeleteObjects;
import io.rjdev.booster.util.aws.s3.GetObjectData;
import io.rjdev.booster.util.aws.s3.ListObjects;
import io.rjdev.booster.util.aws.s3.PutObject;
import lombok.Builder;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;


@Builder
public class AwsUtil {

    private static final String ACCESS_KEY="access_key";
    private static final String SECRET_ACCESS_KEY="secret_access_key";
    private S3Client s3;
    Region region;
    private String access_key;
    private String secret_access_key;

    public void loadKeys(){
        Resource resource = Resource.getInstance();

        access_key = resource.get(ACCESS_KEY);
        secret_access_key = resource.get(SECRET_ACCESS_KEY);
    }

    public AwsUtil awsClient(){
        awsClientWithCredentials();
        return this;
    }

    private void awsClientWithCredentials(){
        loadKeys();
        AwsBasicCredentials credentials =
        AwsBasicCredentials.create(
            access_key,
            secret_access_key
        );

        if(region==null)
            region = Region.US_EAST_1;

        s3 = S3Client.builder()
            .region(region)
            .credentialsProvider(() -> credentials)
            .build();
    }

    public void listAllBuckets(){
        List<Bucket> buckets = s3.listBuckets().buckets();

        for (Bucket bucket : buckets) {
            System.out.println(bucket.name());
        }
    }

    public void uploadToS3(String bucket_name, String objectKey, String filePath){

        String res = PutObject.putS3Object(s3, bucket_name, objectKey, filePath);
        System.out.println(res);
        System.out.println("File uploaded successfully.");
    }


    public void listAllObjects(String bucket_name) {

        ListObjects.listBucketObjects(s3, bucket_name);
    }

    public List<String> listObjects(String bucket_name, String folder_name) {

        return ListObjects.listObjects(s3, bucket_name, folder_name);
    }

    /**
     * Get data object from S3.
     *
     * @param bucket_name The Amazon S3 bucket name.
     * @param keyName The key name.
     * @param path The path where the file is written to.
     */
    public void downloadFromS3(String bucket_name, String keyName, String path){

        GetObjectData.getObjectBytes(s3, bucket_name, keyName, path);

    }

    /**
     * Delete a object in S3 bucket.
     *
     * @param bucket_name The Amazon S3 bucket to delete the website configuration from.
     * @param objectName The object name.
     */
    public void deleteObjectS3(String bucket_name, String objectName) {

        System.out.println("Deleting "+objectName +" from the Amazon S3 bucket: " + bucket_name);
        DeleteObjects.deleteBucketObjects(s3, bucket_name, objectName);
    }

}
