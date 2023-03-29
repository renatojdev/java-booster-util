package io.rjdev.booster.util.img;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void test_writeImage_generateThumbnail(){
        assert ImgUtil.writeImage(bufImage, ImgUtil.EXT_JPG, new File("src/main/resources/200.jpg"));
        BufferedImage bufThumb = ImgUtil.generateThumbnail(bufImage, null, null, "jpg");
        assert ImgUtil.writeImage(bufThumb, ImgUtil.EXT_JPG, new File("src/main/resources/200t.jpg"));
    }

    @Test
    public void test_writeJpgMaxHigh(){
        assert ImgUtil.writeJpgMaxHigh(bufImage, ImgUtil.EXT_JPG, new File("src/main/resources/200.jpg"));
    }

    @Test
    public void test_getFormat(){
        try {
            String format = ImgUtil.getFormat(new FileInputStream("src/main/resources/200t.jpg"));
            System.out.println(format);
            assert "JPEG".equals(format);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_loadImage(){
        try {
            BufferedImage bi = ImgUtil.loadImage(new FileInputStream("src/main/resources/200t.jpg"));
            assert(bi != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_saveImageAsJPEG(){
        try {
            OutputStream outs = new FileOutputStream(new File("src/main/resources/200.jpg"));
            assert ImgUtil.saveImageAsJPEG(bufImage, outs);
            outs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_saveImageAsJPEGQuality(){
        try {
            OutputStream outs = new FileOutputStream(new File("src/main/resources/200.jpg"));
            assert ImgUtil.saveImageAsJPEG(bufImage, outs, 60);
            outs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_saveImageAsPNG(){
        try {
            OutputStream outs = new FileOutputStream(new File("src/main/resources/200.png"));
            assert ImgUtil.saveImageAsPNG(bufImage, outs);
            outs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
