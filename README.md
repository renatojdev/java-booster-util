
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
  <artifactId>booster-util</artifactId>
  <version>0.0.1</version>
</dependency>
```


## Examples code

# AWS SDK

>Set aws s3 credentials on props file **env.properties** Eg:
```
access_key=IAM ACCESS KEY
secret_access_key=IAM SECRET ACCESS KEY
default_bucket_name=DEFAULT S3 BUCKET NAME
```

---
# S3

```java
import io.rjdev.booster.util.aws.AwsUtil;

AwsUtil awsu = AwsUtil.builder()
  .region(Region.US_EAST_1).build()
  .awsClient("access_key", "secret_access_key"); // instance with credentials keys

or

AwsUtil awsu = AwsUtil.builder().build().awsClient();// get from props file

String bucket_name = "bucket_name";

// send the object - bucketName, objectKey, objectPath
awsu.uploadToS3(bucket_name, "file-test.txt", "src/main/resources/file-test.txt");

// get from S3 - bucket_name, keyName, path The path where the file is written to.
awsu.downloadFromS3(bucket_name, "file-test.txt", "src/main/resources/data/file-test-s3.txt");

// delete object
awsu.deleteObjectS3(bucket_name, "file-test.txt");
```
---

# File - Saving bytes

```java
import io.rjdev.booster.util.file.BytesUtil;

String fname = "src/main/resources/data/f1.txt";//file path
boolean saved = BytesUtil.saveBytesToFile(fname, "Hello!".getBytes());
```

# Image - Saving image and thumbnail

```java
//get and save JPG image
BufferedImage bufImage = ImageIO.read(new URL("https://httpcats.com/200.jpg"));
ImgUtil.writeImage(bufImage, "jpg", new File("src/main/resources/200.jpg");

//thumb
BufferedImage bufThumb = ImgUtil.generateThumbnail(bufImage, 144, 144, "jpg");
ImgUtil.writeImage(bufThumb, "jpg", new File("src/main/resources/200t.jpg"));
```

# String tokenizer

```java
import io.rjdev.booster.util.string.Token;

String[] array = Token.separate("Hello, world!", ",");

System.out.println(array[0]);
Output: Hello`
System.out.println(array[1]);
Output: world!`
```

# Hash SHA 256 / 512

```java
import io.rjdev.booster.util.string.SHAString;
import io.rjdev.booster.util.string.SHAString.Type;

String sHash256 = SHAString.hash("string to be converted in sha 256", Type.SHA_256);
```

## License

Apache 2.0

**Free Software!**
