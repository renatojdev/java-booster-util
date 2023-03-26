package io.rjdev.booster.util.aws.s3;

import io.rjdev.booster.util.aws.AwsClientServices;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

public class S3FireClient extends AwsClientServices<S3Client>{
    private static S3FireClient instance = null;

    private S3FireClient(){}

    public static S3FireClient getInstance() {
        if (instance == null) {
            instance = new S3FireClient();
        }
        return instance;
    }

    @Override
    public S3Client buildClient(Region region) {
        if(region != null)
            super.region = region;

        return S3Client.builder()
            .region(super.region)
            .credentialsProvider(() -> credentials())
            .build();
    }

}
