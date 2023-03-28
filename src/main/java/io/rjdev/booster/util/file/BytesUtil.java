package io.rjdev.booster.util.file;


public class BytesUtil {
    private static final int DEFAULT_CHUNK_SIZE = 1024;


    /**
      * Save bytes to file.
      * @param fileName name of the file to write the bytes to
      * @param theBytes the bytes to write to the file
      * @throws java.io.IOException reports problems saving the file;
      */
    public static boolean saveBytesToFile( String fileName, byte[] theBytes ){
      try{
        saveBytesToStream( new java.io.FileOutputStream( fileName ), theBytes );
        return true;
      }catch(java.io.IOException e){
        return false;
      }
    }

    /**
     * save bytes to output stream and close the output stream on success and
     * on failure.
     * @param out the output stream to write the supplied bytes
     * @param theBytes the bytes to write out
     * @throws java.io.IOException reports problems saving bytes to output stream
     */
    public static void saveBytesToStream( java.io.OutputStream out, byte[] theBytes )
        throws java.io.IOException {
      try {
        out.write( theBytes );
      }
      finally {
        out.flush();
        out.close();
      }
    }

    /**
     * Loads bytes from the file
     *
     * @param fileName file to read the bytes from
     * @return bytes read from the file
     * @exception java.io.IOException reports IO failures
     */
    public static byte[] loadBytesFromFile( String fileName ) throws java.io.IOException {
      return loadBytesFromStream( new java.io.FileInputStream( fileName ), DEFAULT_CHUNK_SIZE );
    }

    /**
     * Loads bytes from the given input stream until the end of stream
     * is reached.  It reads in at kDEFAULT_CHUNK_SIZE chunks.
     *
     * @param stream to read the bytes from
     * @return bytes read from the stream
     * @exception java.io.IOException reports IO failures
     */
    public static byte[] loadBytesFromStream( java.io.InputStream in ) throws java.io.IOException {
      return loadBytesFromStream( in, DEFAULT_CHUNK_SIZE );
    }

    /**
     * Loads bytes from the given input stream until the end of stream
     * is reached.  Bytes are read in at the supplied <code>chunkSize</code>
     * rate.
     *
     * @param stream to read the bytes from
     * @return bytes read from the stream
     * @exception java.io.IOException reports IO failures
     */
    public static byte[] loadBytesFromStream( java.io.InputStream in, int chunkSize )
        throws java.io.IOException {
      if( chunkSize < 1 )
        chunkSize = DEFAULT_CHUNK_SIZE;

      int count;
      java.io.ByteArrayOutputStream bo = new java.io.ByteArrayOutputStream();
      byte[] b = new byte[chunkSize];
      try {
        while( ( count = in.read( b, 0, chunkSize ) ) > 0 ) {
          bo.write( b, 0, count );
        }
        byte[] thebytes = bo.toByteArray();
        return thebytes;
      }
      finally {
        bo.close();
      }
    }
  }