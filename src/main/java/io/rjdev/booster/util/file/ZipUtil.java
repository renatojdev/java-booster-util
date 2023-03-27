package io.rjdev.booster.util.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


/**
 * Class containing utilities for ZIP files.
 *
 * @author Gustavo Dugonski
 * @since 14/01/2008
 */
public class ZipUtil {


	/**
     * Performs the extraction of the ZIP file to the destination directory informed
     *
     * @param zipFile <code>FileInputStream</code> Zip file to be extracted
     * @param destDir <code>File</code> directory where to perform the extraction
     *
     * @see FileInputStream
     * @see BufferedInputStream
     * @see ZipInputStream
     * @see ZipEntry
     * @author Gustavo Dugonski
     * @since 14/01/2008
     */
	public static void unzip(FileInputStream zipFile, File destDir) throws ZipException, IOException{
    	try {
    		if(!destDir.exists())
			  destDir.mkdir();

	    	ZipInputStream zis = new ZipInputStream(new BufferedInputStream(zipFile));
	    	ZipEntry entry;
	    	BufferedOutputStream dest;
	    	int buffer = 2048;
	    	while((entry = zis.getNextEntry()) != null) {
	            int count;
	            byte data[] = new byte[buffer];
	            FileOutputStream fos = new FileOutputStream(
					destDir + File.separator + entry.getName()
					);
	            dest = new BufferedOutputStream(fos, buffer);
	            while((count = zis.read(data, 0, buffer)) != -1){
	               dest.write(data, 0, count);
	            }
	            dest.flush();
	            dest.close();
	         }
	         zis.close();
    	}
    	catch (ZipException e) {
			throw e;
		}
    	catch (IOException e) {
    		throw e;
		}
    }

	/**
	 * Overload unzip.
	 *
	 * @see #unzip(FileInputStream, File)
	 */
	public static boolean unzip(String zipFile, String destDir){
		try {
			unzip(new FileInputStream(new File(zipFile)), new File(destDir));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Creates a zip file for the informed destination directory and a list of
	 * files to be zipped.
	 *
	 * @param Zip file destination directory of zip file
	 * @param ToZip files list of files to be zipped
	 * @throws ZipException in case of error.
	 * @throws IOException In case of error.
	 * @since 10/07/2012
	 * @author Renato P. Eduardo Jr
	 */
	public static Boolean zip(String zipFile, String[] filesToZip){

		if (zipFile == null || filesToZip == null)
			return Boolean.FALSE;

		try{
			// create byte buffer
			byte[] buffer = new byte[1024];

			FileOutputStream fos = new FileOutputStream(zipFile);

			ZipOutputStream zos = new ZipOutputStream(fos);
			zos.setMethod(ZipOutputStream.DEFLATED);

			for (int i=0; i < filesToZip.length; i++) {

				File srcFile = new File(filesToZip[i]);

				FileInputStream fis = new FileInputStream(srcFile);

				// begin writing a new ZIP entry, positions
				// the stream to the start of the entry data
				zos.putNextEntry(new ZipEntry(srcFile.getName()));

				int length;

				while ((length = fis.read(buffer)) > 0) {
					zos.write(buffer, 0, length);
				}

				zos.closeEntry();

				// close the InputStream
				fis.close();
				fis = null;
			}

			// close the ZipOutputStream
			zos.close();
			zos = null;
			fos.close();
			fos = null;
		}catch(IOException ex){
			System.err.println(ex);
			return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}
}
