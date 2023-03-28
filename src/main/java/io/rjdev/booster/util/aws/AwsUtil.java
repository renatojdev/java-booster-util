package io.rjdev.booster.util.aws;

import java.util.List;

import io.rjdev.booster.util.Resource;
import io.rjdev.booster.util.aws.s3.DeleteObjects;
import io.rjdev.booster.util.aws.s3.GetObjectData;
import io.rjdev.booster.util.aws.s3.ListObjects;
import io.rjdev.booster.util.aws.s3.PutObject;
import io.rjdev.booster.util.aws.s3.S3FireClient;
import io.rjdev.booster.util.string.StringUtil;
import lombok.Builder;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;


@Builder
public class AwsUtil {

    private String defaultBucketName;
    private S3Client s3;
    Region region;

    public AwsUtil awsClient(){
        Resource resource = Resource.getInstance();
        defaultBucketName = resource.get("default_bucket_name");

        s3 = S3FireClient.getInstance().buildClient(region);
        return this;
    }

    private String getBucketName(String bucketName){
        if(!StringUtil.isNotBlank(bucketName)){
            if(StringUtil.isNotBlank(defaultBucketName))
                return defaultBucketName;
        }
        return bucketName;
    }

    public void listAllBuckets(){
        List<Bucket> buckets = s3.listBuckets().buckets();

        for (Bucket bucket : buckets) {
            System.out.println(bucket.name());
        }
    }

    public void uploadToS3(String bucket_name, String objectKey, String filePath){
        bucket_name = getBucketName(bucket_name);
        if(bucket_name == null ||
            !StringUtil.isNotBlank(objectKey) ||
            !StringUtil.isNotBlank(filePath))
            return;

        String res = PutObject.putS3Object(s3, bucket_name, objectKey, filePath);
        System.out.println(res);
        System.out.println("File uploaded successfully.");
    }


    public void listAllObjects(String bucket_name) {
        bucket_name = getBucketName(bucket_name);
        if(bucket_name == null)
            return;

        ListObjects.listBucketObjects(s3, bucket_name);
    }

    public List<String> listObjects(String bucket_name, String folder_name) {
        bucket_name = getBucketName(bucket_name);
        if(bucket_name == null ||
            !StringUtil.isNotBlank(folder_name))
            return null;

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
        bucket_name = getBucketName(bucket_name);
        if(bucket_name == null ||
            !StringUtil.isNotBlank(keyName) ||
            !StringUtil.isNotBlank(path))
            return;

        GetObjectData.getObjectBytes(s3, bucket_name, keyName, path);

    }

    /**
     * Delete a object in S3 bucket.
     *
     * @param bucket_name The Amazon S3 bucket to delete the website configuration from.
     * @param objectName The object name.
     */
    public void deleteObjectS3(String bucket_name, String objectName) {
        bucket_name = getBucketName(bucket_name);
        if(bucket_name == null || !StringUtil.isNotBlank(objectName))
            return;

        System.out.println("Deleting "+objectName +" from the Amazon S3 bucket: " + bucket_name);
        DeleteObjects.deleteBucketObjects(s3, bucket_name, objectName);
    }

}
