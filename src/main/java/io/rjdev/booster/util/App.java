/** package */
package io.rjdev.booster.util;

/** imports */
import io.rjdev.booster.util.aws.AwsUtil;

/**
 * App to send or delete objects to S3.
 */
public class App {
    public static void main( String[] args ){
        String operation = args[0]; // operation put | del
        String BUCKET_NAME = args[1]; //args[1] The Amazon S3 bucket name.
        String objectKey = args[2]; // args[2] objectKey in the bucket.
        String objectPath = args[3]; // args[3] File path to send to s3
        AwsUtil awsUtil = AwsUtil.builder()
			.build()
			.awsClient(AwsUtil.Region_US_WEST_2);

        if(operation.equals("put")){ // upload to s3

            awsUtil.uploadToS3(BUCKET_NAME, objectKey, objectPath);

        } else if(operation.equals("del")){ // delete to s3
            awsUtil.deleteObjectS3(BUCKET_NAME, objectKey);

        } else {
            System.out.println("Invalid command.");
        }

        // AwsUtil awsu = AwsUtil.builder().build();
        // awsu.awsClient();

        // awsu.listAllBuckets();
        // awsu.uploadToS3("bucket_name", "file-test.txt", "src/main/resources/file-test.txt");

        // awsu.listAllObjects(bucket_name);
        // awsu.downloadFromS3("bucket_name", "file-test.txt", "src/main/resources/data/file-test-s3.txt");

        // awsu.deleteObjectS3(bucket_name, "file-test.txt");

    }
}
