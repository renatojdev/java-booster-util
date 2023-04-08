package io.rjdev.booster.util.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import lombok.Builder;

@Builder
public class Get {

    @Builder.Default
    String endpoint = "https://yoururl.com";
    Map<String,String> headers;
    Map<String,String> parameters;
    private int status;
    private String response;
    private HttpURLConnection con;

    public int getStatus() {
        return status;
    }

    public String getResponseBody(){
        return response;
    }

    private void createConnection(){
        try{
            addParameters();
            System.out.println(endpoint);
            URL obj = new URL(endpoint);
            this.con = (HttpURLConnection) obj.openConnection();
            configureConnectionDefaults();
            addHeaders();

        }catch(IOException ioe){
            System.err.println(ioe.getMessage());
        }
    }

    private void configureConnectionDefaults(){
        if(this.con != null){
            try {
                this.con.setRequestMethod("GET");
                this.con.setConnectTimeout(5000);
                this.con.setReadTimeout(5000);
                System.out.println("defaults configured..");
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private void addParameters() {

        try {
            // ADD PARAMETERS
            if(parameters!=null)
                endpoint = endpoint + "?" + ParameterStringBuilder.getParamsString(parameters);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(System.out);
        }
    }

    public Get execute() throws Exception{
        createConnection();

        this.con.setInstanceFollowRedirects(false);
        HttpURLConnection.setFollowRedirects(false);

        status = this.con.getResponseCode();
        System.out.println("GET status code:::"+ status);

        if (status == HttpURLConnection.HTTP_MOVED_TEMP
            || status == HttpURLConnection.HTTP_MOVED_PERM) {// 301 | 302
                String location = this.con.getHeaderField("Location");

                URL newUrl = new URL(location);
                this.con = (HttpURLConnection) newUrl.openConnection();
                configureConnectionDefaults();
        }

        if (status == HttpURLConnection.HTTP_OK) { //200 - success
            System.out.println("Reading response...");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            // print result
            this.response = content.toString();

            return this;
        } else {
            System.out.println("GET request not worked");
        }

        return null;

    }

    private void addHeaders() {
        if(this.con == null){
            return;
        }

        // add request default headers
        this.con.setRequestProperty("User-Agent", "Mozilla/5.0");
        this.con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        if(headers!=null)
            headers.entrySet().stream()
            .forEach(e -> {
                this.con.setRequestProperty(e.getKey(), e.getValue());
            });
    }

}
