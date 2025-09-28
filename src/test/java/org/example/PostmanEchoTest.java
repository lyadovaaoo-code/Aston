package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class PostmanEchoTest {

    private static final String BASE_URL = "https://postman-echo.com";
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();
    private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    // GET Method
    @Test
    void testGetMethod() throws IOException {
        String url = BASE_URL + "/get?foo1=bar1&foo2=bar2";

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            // Проверяем статус код
            assertEquals(200, response.code(), "Status code should be 200");

            // Проверяем тело ответа
            String responseBody = response.body().string();
            JsonNode jsonResponse = mapper.readTree(responseBody);

            assertEquals("bar1", jsonResponse.get("args").get("foo1").asText());
            assertEquals("bar2", jsonResponse.get("args").get("foo2").asText());
            assertEquals(url, jsonResponse.get("url").asText());
        }
    }

    // POST Method
    @Test
    void testPostMethod() throws IOException {
        String url = BASE_URL + "/post";
        String jsonBody = "{\"key\":\"value\",\"name\":\"test user\"}";

        RequestBody body = RequestBody.create(jsonBody, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            assertEquals(200, response.code(), "Status code should be 200");

            String responseBody = response.body().string();
            JsonNode jsonResponse = mapper.readTree(responseBody);

            assertEquals("value", jsonResponse.get("json").get("key").asText());
            assertEquals("test user", jsonResponse.get("json").get("name").asText());
        }
    }

    // PUT Method
    @Test
    void testPutMethod() throws IOException {
        String url = BASE_URL + "/put";
        String jsonBody = "{\"id\":1,\"title\":\"Updated Title\"}";

        RequestBody body = RequestBody.create(jsonBody, JSON);
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            assertEquals(200, response.code(), "Status code should be 200");

            String responseBody = response.body().string();
            JsonNode jsonResponse = mapper.readTree(responseBody);

            assertEquals(1, jsonResponse.get("json").get("id").asInt());
            assertEquals("Updated Title", jsonResponse.get("json").get("title").asText());
        }
    }

    // PATCH Method
    @Test
    void testPatchMethod() throws IOException {
        String url = BASE_URL + "/patch";
        String jsonBody = "{\"status\":\"patched\"}";

        RequestBody body = RequestBody.create(jsonBody, JSON);
        Request request = new Request.Builder()
                .url(url)
                .patch(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            assertEquals(200, response.code(), "Status code should be 200");

            String responseBody = response.body().string();
            JsonNode jsonResponse = mapper.readTree(responseBody);

            assertEquals("patched", jsonResponse.get("json").get("status").asText());
        }
    }

    // DELETE Method
    @Test
    void testDeleteMethod() throws IOException {
        String url = BASE_URL + "/delete?item_id=123";

        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();

        try (Response response = client.newCall(request).execute()) {
            assertEquals(200, response.code(), "Status code should be 200");

            String responseBody = response.body().string();
            JsonNode jsonResponse = mapper.readTree(responseBody);

            assertEquals("123", jsonResponse.get("args").get("item_id").asText());
            assertTrue(jsonResponse.get("url").asText().contains("/delete"));
        }
    }
}