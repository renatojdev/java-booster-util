package io.rjdev.booster.util.aws;

import io.rjdev.booster.util.Resource;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;

public abstract class AwsClientServices<T> {
    private static final String ACCESS_KEY="access_key";
    private static final String SECRET_ACCESS_KEY="secret_access_key";
    protected Region region = Region.US_EAST_1;

    public abstract T buildClient(Region region);

    protected AwsBasicCredentials credentials(){
        Resource resource = Resource.getInstance();

        String access_key = resource.get(ACCESS_KEY);
        String secret_access_key = resource.get(SECRET_ACCESS_KEY);

        return AwsBasicCredentials.create(
            access_key,
            secret_access_key
        );
    }
}
