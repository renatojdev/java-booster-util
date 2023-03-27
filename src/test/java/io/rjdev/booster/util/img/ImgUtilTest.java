package io.rjdev.booster.util.img;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ImgUtilTest {
    static URL url;
    static BufferedImage bufImage;

    @BeforeAll
    public static void start() {
        System.out.println("=======Starting junit 5 tests========");
        try{
            url = new URL("https://httpcats.com/200.jpg");
            bufImage = ImageIO.read(url);
        }catch(IOException e){}
    }

    @Test
    public void test_writeImage_generateThumbnail(){
        assert(ImgUtil.writeImage(bufImage, "jpg", new File("src/main/resources/200.jpg")));
        BufferedImage bufThumb = ImgUtil.generateThumbnail(bufImage, null, null, "jpg");
        assert(ImgUtil.writeImage(bufThumb, "jpg", new File("src/main/resources/200t.jpg")));
    }

    @Test
    public void test_writeJpgMaxHigh(){
        assert(ImgUtil.writeJpgMaxHigh(bufImage, "jpg", new File("src/main/resources/200.jpg")));
    }

}
