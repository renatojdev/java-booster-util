package io.rjdev.booster.util.aws;

import io.rjdev.booster.util.Resource;
import io.rjdev.booster.util.string.StringUtil;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;

public abstract class AwsClientServices<T,S> {
    private static final String ACCESS_KEY="access_key";
    private static final String SECRET_ACCESS_KEY="secret_access_key";
    protected Region region = Region.US_EAST_1;
    protected String access_key;
    protected String secret_access_key;

    public abstract T buildClient(Region region);
    public abstract S buildAsyncClient(Region region);

    /**
     * Create the basic credentials.
     *
     * @return <code>null</code> if not keys found.
     */
    protected AwsBasicCredentials credentials(){
        if(!StringUtil.isNotBlank(access_key) &&
            !StringUtil.isNotBlank(secret_access_key)){
                Resource resource = Resource.getInstance();
                access_key = resource.get(ACCESS_KEY);
                secret_access_key = resource.get(SECRET_ACCESS_KEY);
        }

        if(access_key==null || secret_access_key==null)
            return null;

        return AwsBasicCredentials.create(
            access_key,
            secret_access_key
        );
    }

    protected ProfileCredentialsProvider defaultProvider(){
        return ProfileCredentialsProvider.create();
    }
}
