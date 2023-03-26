package io.rjdev.booster.util.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
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
            URL url = new URL(endpoint);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
        }catch(IOException ioe){
            System.err.println(ioe.getMessage());
        }
    }

    private void addParameters() {

        try {
            // ADD PARAMETERS
            endpoint = endpoint + "?" + ParameterStringBuilder.getParamsString(parameters);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(System.out);
        }
    }

    public Get execute() throws Exception{

        createConnection();
        addHeaders();

        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        con.setInstanceFollowRedirects(false);
        HttpURLConnection.setFollowRedirects(false);

        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.flush();
        out.close();

        status = con.getResponseCode();
        System.out.println("GET Response Code :: " + status);

        if (status == HttpURLConnection.HTTP_MOVED_TEMP
            || status == HttpURLConnection.HTTP_MOVED_PERM) {
                String location = con.getHeaderField("Location");
                URL newUrl = new URL(location);
                con = (HttpURLConnection) newUrl.openConnection();
        }

        if (status == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();

            // print result
            System.out.println(content.toString());
            this.response = content.toString();

            return this;
        } else {
            System.out.println("GET request not worked");
        }

        return null;

    }

    private void addHeaders() {

        if(con == null)
            return;

        // add request default headers
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        if(headers!=null)
            headers.entrySet().stream()
            .forEach(e -> {
                System.out.println("ADD HEADER: "+ e.getKey() + ":" + e.getValue());
                con.setRequestProperty(e.getKey(), e.getValue());
            });

    }

}
