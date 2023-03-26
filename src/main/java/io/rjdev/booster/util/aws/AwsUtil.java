package io.rjdev.booster.util.aws;

import java.util.List;

import io.rjdev.booster.util.aws.s3.DeleteObjects;
import io.rjdev.booster.util.aws.s3.GetObjectData;
import io.rjdev.booster.util.aws.s3.ListObjects;
import io.rjdev.booster.util.aws.s3.PutObject;
import io.rjdev.booster.util.aws.s3.S3FireClient;
import lombok.Builder;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;


@Builder
public class AwsUtil {

    private S3Client s3;
    Region region;

    public AwsUtil awsClient(){
        s3 = S3FireClient.getInstance().buildClient(region);
        // if(region != null){
        //     S3ClientOne s3One = S3ClientOne.getInstance();
        //     s3One.setRegion(region);
        //     s3 = s3One.buildClient();
        // } else s3 = S3ClientOne.getInstance().buildClient();
        return this;
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
