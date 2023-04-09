package io.rjdev.booster.util.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import lombok.Builder;

@Builder
public class Post {

    @Builder.Default
    String endpoint = "https://yoururl.com";
    @Builder.Default
    String body = "{}";
    Map<String,String> headers;
    private int responseCode;
    private String response;
    private HttpURLConnection con;

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponse(){
        return response;
    }

    private void createConnection(){
        try{
            URL url = new URL(endpoint);
            System.out.println("Post to endpoint: "+ endpoint);
            this.con = (HttpURLConnection) url.openConnection();
            configureConnectionDefaults();

            if(this.con != null)
                addHeaders();

        }catch(IOException ioe){
            System.err.println(ioe.getMessage());
        }
    }

    private void configureConnectionDefaults(){
        if(this.con != null){
            try {
                this.con.setRequestMethod("POST");
                this.con.setConnectTimeout(5000);
                this.con.setReadTimeout(5000);

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public Post execute() throws Exception{
        createConnection();

        // For POST only - START
        con.setDoOutput(true);
        DataOutputStream os = new DataOutputStream(con.getOutputStream());
        os.writeBytes(body);
        os.flush();
        os.close();
        // For POST only - END

        responseCode = con.getResponseCode();
        System.out.println("POST Response Code:::" + responseCode);

        if (responseCode >= HttpURLConnection.HTTP_OK &&
            responseCode <= HttpURLConnection.HTTP_PARTIAL) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in .readLine()) != null) {
                response.append(inputLine);
            } in .close();

            // print result
            this.response = response.toString();

            return this;
        } else {
            System.out.println("POST request not worked");
        }

        return null;

    }

    private void addHeaders() {

        if(this.con == null)
            return;

        // add request default headers
        this.con.setRequestProperty("User-Agent", "Mozilla/5.0");
        this.con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        if(this.headers!=null)
            this.headers.entrySet().stream()
                .forEach(e -> {
                    System.out.println("ADD HEADER: "+ e.getKey() + ":" + e.getValue());
                    this.con.setRequestProperty(e.getKey(), e.getValue());
                });

    }

}
