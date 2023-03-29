package io.rjdev.booster.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Resource {
    private static final Properties prop;

    private static void loadResource(){
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("env.properties");
        try {
            prop.load(stream);
            stream.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }finally{
            loader=null;
        }
    }

    /*
     * Get propertie string from key value.
     */
    public static String get(String key){
        return prop.getProperty(key);
    }

    static {
        prop = new Properties();
        loadResource();
    }

}
