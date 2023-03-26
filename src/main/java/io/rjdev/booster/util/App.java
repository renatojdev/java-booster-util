/** package */
package io.rjdev.booster.util;

/** imports */
import org.apache.commons.lang3.StringUtils;

import io.rjdev.booster.util.aws.AwsUtil;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ){
        System.out.println( "Hello World!" );
        System.out.println(StringUtils.compare("Oi", "Oi"));

        String bucket_name = "devel2";
        AwsUtil awsu = AwsUtil.builder().build();
        awsu.awsClient();

        // awsu.listAllBuckets();
        // awsu.uploadToS3("devel2", "file-test.txt", "src/main/resources/file-test.txt");

        awsu.listAllObjects(bucket_name);
        // awsu.downloadFromS3("devel2", "file-test.txt", "src/main/resources/data/file-test-s3.txt");

        // awsu.deleteObjectS3(bucket_name, "file-test.txt");

    }
}
