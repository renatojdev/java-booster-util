package io.rjdev.booster.util.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;

/**
 * File Util class.
 *
 */
@Getter @Setter
public class FileUtil {

	private String path;
	private String fileNameString;

	public void FitUtil (String path){
		this.path = path;
	}

	public String getPath(){
		return this.path;
	}

	public void setPath(String path){
		this.path = path;
	}

	/**
	 * Opens a file and returns a String with all
	 * the contents of this file.
	 *
	 * @author Ronaldo Campos de Oliveira
	 * @since 30/07/2008
	 * @param filePath File path
	 * @return File content String
	 * @throws FileNotFoundException, IOException
	 */
	 public static String fileToString(String filePath)
	 	throws FileNotFoundException, IOException{
		String content = "";
		File file = new File(filePath);
		BufferedReader br = new BufferedReader(new FileReader(file));
		StringBuffer bufOut = new StringBuffer();

		// File contents to a string
		String line; int i = 0;
		while( (line = br.readLine()) != null ){
			bufOut.append(line);
			if(i>0)
				bufOut.append("\n");
		}
		// buffer's close an unset file
		br.close();
		file = null;
		content = bufOut.toString();

		return content;
	}

	 /**
		 * Method to create a directory according to the specified path.
		 *
		 * @return <code>true</code> if was created dir or
		 *         <code>false</code> otherwise.
		 */
		public Boolean mkdir(){
			Boolean created = Boolean.FALSE;

			if(path==null)
				return Boolean.FALSE;

			File file = new File(path);
			if (!file.exists()) {
				created = file.mkdir();
			}
			return created;
		}

		/**
		 * Method to save file receiving an array of Bytes.
		 * @param bytes
		 * @return true if file was save successfully
		 */
		public Boolean fileSave(byte[] bytes){
			if(!hasFile())
				return Boolean.FALSE;
			try {
				BytesUtil.saveBytesToFile(path+File.separator+fileNameString, bytes);
			} catch (IOException e) {
				return Boolean.FALSE;
			}
			return Boolean.TRUE;
		}

		/**
		 * Metodo to load the bytes from file.
		 * @return byte[] do arquivo.
		 */
		public byte[] loadBytesFromFile(){
			byte[] bytes = null;
			if(!hasFile())
				return bytes;
			try {
				bytes = BytesUtil.loadBytesFromFile(path+File.separator+fileNameString);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return bytes;
		}

		/**
		 * Method to delete a file from a directory and receive flag
		 * to delete the directory if it does not contain any files.
		 *
		 * @param delEmptyDir Boolean to delete the empty directory.
		 * @return Boolean TRUE if the operations were successfully performed or
		 * FALSE otherwise.
		 */
		public Boolean delete(Boolean delEmptyDir){
			Boolean res = Boolean.FALSE;

			if(!hasFile())
			  return res;

			try {
				File f = new File(path + File.separator + fileNameString);
				if (f.exists())
					res = f.delete();

				if (delEmptyDir) {
					f = new File(path);
					// If the directory has no file then delete the directory
					File[] diretorio = f.listFiles();
 					if (diretorio == null || diretorio.length == 0)
						res = res && f.delete();
				}
			} catch (SecurityException e) {
				System.out.println("It was not possible to carry out the operations.");
				return Boolean.FALSE;
			}

			return res;
		}

		private boolean hasFile() {
			return !(path==null||fileNameString==null);
		}

		/**
		 * Returns the number of files in the directory. If directory not found,
		 * -1 and returned.
         *
         * @return number of files in directory or -1 in case of directory
         * not found.
		 */
		public int dirFilesLength(){
			if(path==null)
				return -1;
			File f = new File(path);
			if (f.exists()){
				File[] files = f.listFiles();
				if(files == null) return 0;
				else return files.length;
			}

			return -1;
		}
    }