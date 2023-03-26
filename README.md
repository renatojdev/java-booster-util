
# Java Booster Util

This project is a Java helper library with a primary focus on providing resources and functionalities related to AWS, S3, validation and string manipulation, image conversion. In addition, has helper classes for handling HTTP requests, such as GET, POST, PUT, and DELETE.

Contain useful classes and methods for dealing with AWS services, such as creating and managing EC2 instances(in the future), storing and retrieving files in S3, configuring SNS notifications(in the future).

With maven can be easily integrated into other existing projects.


## This project usage:

1. Java 11
2. Maven
3. AWS SDK Java 2 - v2.20.20

## Getting Started

**Prerequisite**
* Maven - [Download](https://maven.apache.org/download.cgi).

* Set up your development environment by following the instructions in Set up the AWS SDK for Java 2.x. [Setup](https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/setup.html)

**Installation this project on local repository**

1. Git Clone
```sh
git clone https://github.com/renatojdev/java-booster-util.git
```
2. Install `~/.m2/repository`

```sh
mvn clean install
```

## Adding in your java project

```
<dependency>
  <groupId>io.rjdev.booster</groupId>
  <artifactId>util</artifactId>
  <version>0.0.1</version>
</dependency>
```


## Example code

```java
import io.rjdev.booster.util.aws.AwsUtil;

AwsUtil awsu = awsu = AwsUtil.builder().build().awsClient();

String bucket_name = "bucket_name";
// send the object - bucketName, objectKey, objectPath
awsu.uploadToS3(bucket_name, "file-test.txt", "src/main/resources/file-test.txt");
```

## License

Apache 2.0

**Free Software!**
