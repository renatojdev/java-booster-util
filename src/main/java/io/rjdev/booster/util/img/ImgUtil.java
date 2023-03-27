package io.rjdev.booster.util.img;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;

public class ImgUtil {

	private static int DEFAULT_THUMB_WIDTH = 244;
	private static int DEFAULT_THUMB_HEIGHT = 244;

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

		boolean isPNG = ext.toLowerCase().equals("png");

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

		if (!(ext.toUpperCase().equals("JPG")
				|| ext.toUpperCase().equals("JPEG")))// Somente JPG
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

}
