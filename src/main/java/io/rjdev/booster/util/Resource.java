package io.rjdev.booster.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Resource {
    private static Resource instance = null;
    Properties prop = new Properties();

    private Resource() {
        loadResource();
    }


    public static Resource getInstance() {
        if (instance == null) {
            instance = new Resource();
        }
        return instance;
    }

    private void loadResource(){
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("env.properties");

        try {
            prop.load(stream);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    /*
     * Get propertie string from key value.
     */
    public String get(String key){
        return prop.getProperty(key);
    }


}
