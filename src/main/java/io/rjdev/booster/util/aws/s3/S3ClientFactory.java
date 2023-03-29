package io.rjdev.booster.util.aws.s3;

import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.transfer.s3.S3TransferManager;


/**
 * Before running this Java V2 code example, set up your development environment, including your credentials.
 *
 * For more information, see the following documentation topic:
 *
 * https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
 */

public class S3ClientFactory {
    public static final S3TransferManager transferManager = createCustomTm();
    // public static final S3TransferManager transferManagerWithDefaults = createDefaultTm();
    public static S3Client s3Client;

    private static S3TransferManager createCustomTm(){
        // snippet-start:[s3.tm.java2.s3clientfactory.create_custom_tm]
        S3AsyncClient s3AsyncClient =
            S3FireClient.getInstance().buildAsyncClient(null);

        S3TransferManager transferManager =
            S3TransferManager.builder()
                .s3Client(s3AsyncClient)
                .build();
        // snippet-end:[s3.tm.java2.s3clientfactory.create_custom_tm]
        return transferManager;
    }

    public static void setCustomTmCredentials(String access_key, String secret_access_key){
        S3FireClient.getInstance().credentials(access_key, secret_access_key);
        s3Client = S3FireClient.getInstance().buildClient(null);
    }

    static {
        s3Client = S3FireClient.getInstance().buildClient(null);
    }

}
