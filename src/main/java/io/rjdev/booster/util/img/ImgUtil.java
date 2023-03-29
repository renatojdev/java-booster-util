package io.rjdev.booster.util.img;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Locale;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

public class ImgUtil {

	private static final int DEFAULT_THUMB_WIDTH = 244;
	private static final int DEFAULT_THUMB_HEIGHT = 244;
	private static final String FORMAT_JPG = "JPEG";
	private static final String FORMAT_PNG = "PNG";
	public static final String EXT_JPG = "jpg";
	public static final String EXT_PNG = "png";

	public static boolean writeImage(BufferedImage bufImage, String extensao, File imageFile){
		boolean success = true;
		// Writes the Image to the disc
		try {
			ImageIO.write(bufImage, extensao, imageFile);

		} catch (IOException e) {
			success = false;
			System.err.println("Error writes the Image.");
		}

		return success;
	}

	/**
	 * Method that generates a thumbnail for the image passed as a parameter.
	 * Uses the parameters of the class itself, if the width and height
	 * reported to be void.
	 *
	 * @param original
	 *            - Original image, life-size
	 * @param width
	 *            - Width to be used
	 * @param height
	 *            - Height to be used
	 *
	 * @return the thumbnail generated for the original image
	 */
	public static BufferedImage generateThumbnail(BufferedImage original,
			Integer width, Integer height, String ext) {

		boolean isPNG = ext.toLowerCase().equals(FORMAT_PNG);

		Image scaled = (width != null && height != null) ? original
				.getScaledInstance(width, height, Image.SCALE_SMOOTH)
				: original.getScaledInstance(DEFAULT_THUMB_WIDTH, DEFAULT_THUMB_HEIGHT,
						Image.SCALE_SMOOTH);

		int transparency = BufferedImage.TYPE_INT_RGB;

		if(isPNG)
			transparency = BufferedImage.BITMASK;

		BufferedImage thumbnail = (width != null && height != null) ? new BufferedImage(
				width, height, transparency) : new BufferedImage(
				DEFAULT_THUMB_WIDTH, DEFAULT_THUMB_HEIGHT, transparency);

		Graphics2D g2d = thumbnail.createGraphics();

		if(isPNG)
			g2d.setComposite(AlphaComposite.Src);

		g2d.drawImage(scaled, 0, 0, null);
		g2d.dispose();

		return thumbnail;
	}

	/**
	 * Method to record JPG images with maximum quality, i.e. without
	 * Compression.
	 *
	 * @param bImage
	 *            Image Buffered
	 * @param extensao
	 *            file extension
	 * @param file
	 *            file to be writes
	 * @throws IOException
	 *            if exception
	 */
	public static boolean writeJpgMaxHigh(BufferedImage bImage, String ext,
			File file) {

		if (!(ext.toLowerCase().equals(ImgUtil.EXT_JPG)
				|| ext.toLowerCase().equals(FORMAT_JPG)))// Somente JPG
			return false;

		try{
			ImageOutputStream ios = ImageIO.createImageOutputStream(file);

			ImageWriter writer = null;
			Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName(ext
					.toUpperCase());
			if (!iter.hasNext())
				throw new IOException("No Writers Available");
			writer = (ImageWriter) iter.next();
			writer.setOutput(ios);
			JPEGImageWriteParam iwp = new JPEGImageWriteParam(null);
			iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			iwp.setCompressionQuality(1.0f);
			writer.write(null, new IIOImage(bImage, null, null), iwp);
			ios.flush();
			writer.dispose();
			ios.close();
		}catch(IOException ioe){
			return false;
		}
		return true;
	}


	public static String getFormat(final InputStream stream) throws IOException {
        final ImageInputStream iis = ImageIO.createImageInputStream(stream);
        final Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
        if (!iter.hasNext()) {
            throw new IOException("Unsupported image format!");
        }
        final ImageReader reader = iter.next();
        iis.close();
        return reader.getFormatName();
    }

    public static BufferedImage loadImage(final InputStream stream) throws IOException {
        return ImageIO.read(stream);
    }

    public static boolean saveImageAsJPEG(final BufferedImage image, final OutputStream stream) {
        try {
			return ImageIO.write(image, FORMAT_JPG, stream);
		} catch (IOException e) {
			System.out.println("ERROR ImgUtil.saveImageAsJPEG: "+ e.getMessage());
			return false;
		}finally{
			try {
				stream.close();
			} catch (IOException e) {}
		}
    }

    public static boolean saveImageAsJPEG(final BufferedImage image, final OutputStream stream, final int qualityPercent) {
		if (qualityPercent < 0 || qualityPercent > 100) {
			throw new IllegalArgumentException("Quality out of bounds!");
		}
		try {
			final float quality = qualityPercent / 100.0f;
			ImageWriter writer = null;
			final Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName(FORMAT_JPG);
			if (iter.hasNext()) {
				writer = iter.next();
			}
			final ImageOutputStream ios = ImageIO.createImageOutputStream(stream);
			writer.setOutput(ios);
			final ImageWriteParam iwparam = new JPEGImageWriteParam(Locale.getDefault());
			iwparam.setCompressionMode(2);
			iwparam.setCompressionQuality(quality);
			writer.write(null, new IIOImage(image, null, null), iwparam);
			ios.flush();
			writer.dispose();
			ios.close();
			return true;
		} catch (Exception e) {
			System.out.println("ERROR ImgUtil.saveImageAsJPEG: "+ e.getMessage());
			return false;
		}
	}

	public static boolean saveImageAsPNG(final BufferedImage image, final OutputStream stream){
		try {
			return ImageIO.write(image, FORMAT_PNG, stream);
		} catch (IOException e) {
			System.out.println("ERROR ImgUtil.saveImageAsPNG: "+ e.getMessage());
			return false;
		}finally{
			try {
				stream.close();
			} catch (IOException e) {}
		}
	}

    private static BufferedImage convertToARGB(final BufferedImage srcImage) {
        final BufferedImage newImage = new BufferedImage(
			srcImage.getWidth(null),
			srcImage.getHeight(null),
			BufferedImage.TYPE_INT_ARGB
		);
        final Graphics bg = newImage.getGraphics();
        bg.drawImage(srcImage, 0, 0, null);
        bg.dispose();
        return newImage;
    }

}
