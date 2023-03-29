//snippet-sourcedescription:[UploadADirectory.java demonstrates how to recursively copy a local directory to an Amazon Simple Storage Service (Amazon S3) bucket the Amazon S3 TransferManager.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-service:[Amazon S3]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/
package io.rjdev.booster.util.aws.s3.tm;

// snippet-start:[s3.tm.java2.uploadadirectory.import]
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.rjdev.booster.util.Resource;
import io.rjdev.booster.util.aws.s3.S3ClientFactory;
import io.rjdev.booster.util.file.FileUtil;
import io.rjdev.booster.util.string.StringUtil;
import software.amazon.awssdk.services.s3.model.DeleteBucketResponse;
import software.amazon.awssdk.services.s3.model.ObjectIdentifier;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.model.CompletedDirectoryUpload;
import software.amazon.awssdk.transfer.s3.model.DirectoryUpload;
import software.amazon.awssdk.transfer.s3.model.UploadDirectoryRequest;

import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Before running this Java V2 code example, set up your development environment, including your credentials.
 *
 * For more information, see the following documentation topic:
 *
 * https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
 */

 public class UploadADirectory {
    private static final Logger logger = LoggerFactory.getLogger(UploadADirectory.class);
    private String bucketName = "x-" + UUID.randomUUID();
    private String sourceDirectory;

    public UploadADirectory() {
        setUp();
    }

    public UploadADirectory(String access_key, String secret_access_key) {
        S3ClientFactory.setCustomTmCredentials(access_key, secret_access_key);
        setUp();
    }

    public Integer upload(String sourceDirectory){
        return upload(sourceDirectory, "");
    }

    public Integer upload(String sourceDirectory, String bucketName){
        if(StringUtil.isNotBlank(sourceDirectory))
            this.sourceDirectory = sourceDirectory;
        if(StringUtil.isNotBlank(bucketName))
            this.bucketName = bucketName;
        return upload();
    }

    public Integer upload() {
        Integer numFailedUploads = uploadDirectory(S3ClientFactory.transferManager, sourceDirectory, bucketName);
        logger.info("Number of failed transfers [{}].", numFailedUploads);
        // cleanUp();
        return numFailedUploads;
    }

    // snippet-start:[s3.tm.java2.uploadadirectory.main]
    public Integer uploadDirectory(S3TransferManager transferManager,
                                   String sourceDirectory, String bucketName){
        DirectoryUpload directoryUpload =
            transferManager.uploadDirectory(UploadDirectoryRequest.builder()
                .source(Paths.get(sourceDirectory))
                .bucket(bucketName)
                .build());

        CompletedDirectoryUpload completedDirectoryUpload = directoryUpload.completionFuture().join();
        completedDirectoryUpload.failedTransfers().forEach(fail ->
            logger.warn("Object [{}] failed to transfer", fail.toString()));
        return completedDirectoryUpload.failedTransfers().size();
    }
    // snippet-end:[s3.tm.java2.uploadadirectory.main]

    private void setUp(){
        S3ClientFactory.s3Client.createBucket(b -> b.bucket(bucketName));
        sourceDirectory = Resource.get("default_upload_dir");
    }

    public boolean cleanUp(){
        System.out.println("cleanUP: "+ bucketName + " sourceDir: "+ sourceDirectory);
        FileUtil fu = new FileUtil(sourceDirectory);
        Set<String> filesSet = fu.listNameFiles();
        Set<ObjectIdentifier> objects = new HashSet<>();
                filesSet.stream().forEach(name -> {
                    objects.add(ObjectIdentifier.builder().key(name).build());
                });
        S3ClientFactory.s3Client.deleteObjects(b -> b
            .bucket(bucketName)
            .delete(b1 -> b1
                .objects(
                    objects
                )));
        DeleteBucketResponse resp = S3ClientFactory.s3Client
            .deleteBucket(b -> b.bucket(bucketName));
        return resp.sdkHttpResponse().isSuccessful();

    }
}
