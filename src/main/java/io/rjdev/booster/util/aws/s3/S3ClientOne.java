package io.rjdev.booster.util.aws.s3;

import io.rjdev.booster.util.Resource;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

/**
 * Class Singlenton to build S3Client object.
 *
 * @author Renato P E Jr - renatojr@outlook.com
 * @since 0.0.1
 */
public class S3ClientOne {
    private static S3ClientOne instance = null;
    private static final String ACCESS_KEY="access_key";
    private static final String SECRET_ACCESS_KEY="secret_access_key";
    Region region = Region.US_EAST_1;
    private String access_key;
    private String secret_access_key;


    public void setRegion(Region region) {
        this.region = region;
    }

    private S3ClientOne(){
        loadKeys();
    }

    public static S3ClientOne getInstance() {
        if (instance == null) {
            instance = new S3ClientOne();
        }
        return instance;
    }

    public void loadKeys(){
        Resource resource = Resource.getInstance();

        access_key = resource.get(ACCESS_KEY);
        secret_access_key = resource.get(SECRET_ACCESS_KEY);
    }

    public S3Client buildClient() {
        AwsBasicCredentials credentials =
        AwsBasicCredentials.create(
            access_key,
            secret_access_key
        );

        return S3Client.builder()
            .region(region)
            .credentialsProvider(() -> credentials)
            .build();
    }

}
