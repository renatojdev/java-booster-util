package io.rjdev.booster.util.aws.s3;

import io.rjdev.booster.util.aws.AwsClientServices;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Client;

import static software.amazon.awssdk.transfer.s3.SizeConstant.MB;

public class S3FireClient extends AwsClientServices<S3Client,S3AsyncClient>{
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

        AwsBasicCredentials creds = credentials();
        return S3Client.builder()
            .region(super.region)
            .credentialsProvider(
                creds != null ?
                () -> creds :
                defaultProvider()
            ).build();
    }

    @Override
    public S3AsyncClient buildAsyncClient(Region region) {
        if(region != null)
            super.region = region;

        AwsBasicCredentials creds = credentials();
        return S3AsyncClient.crtBuilder()
            .credentialsProvider(
                creds != null ?
                () -> creds :
                defaultProvider()
            ).region(super.region)
            .targetThroughputInGbps(20.0)
            .minimumPartSizeInBytes(8 * MB)
            .build();
    }

}
