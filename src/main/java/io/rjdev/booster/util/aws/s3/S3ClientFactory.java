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
    public static final S3TransferManager transferManagerWithDefaults = createDefaultTm();
    public static final S3Client s3Client;

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

    private static S3TransferManager createDefaultTm(){
        // snippet-start:[s3.tm.java2.s3clientfactory.create_default_tm]
        S3TransferManager transferManager = S3TransferManager.create();
        // snippet-end:[s3.tm.java2.s3clientfactory.create_default_tm]
        return transferManager;
    }

    static {
        s3Client = S3FireClient.getInstance().buildClient(null);
    }

}
