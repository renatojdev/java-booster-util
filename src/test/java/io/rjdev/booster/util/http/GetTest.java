package io.rjdev.booster.util.http;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class GetTest {

    @BeforeAll
    public static void start() {
        System.out.println("=======Starting junit 5 tests========");
    }

    @Test
    public void executeTest(){
        String url = "https://dummyjson.com/http/200";

        Map<String,String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("param1", "val");
        parameters.put("param2", "val2");

        try {
            Get get = Get.builder()
            .endpoint(url)
            .headers(headers)
            // .parameters(parameters)
            .build()
            .execute();

            assertNotNull(get);
            assertEquals(HttpURLConnection.HTTP_OK, get.getStatus());

            String body = "{\"status\":\"200\",\"message\":\"OK\"}";
            assertEquals(body, get.getResponseBody());

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void main(String[] args){
        executeTest();
    }
}
