package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Paths;

public class CollectionAnalyzer {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = new String(Files.readAllBytes(Paths.get("postman-collections/postman-echo.json")));
        JsonNode collection = mapper.readTree(content);

        System.out.println("=== COLLECTION ANALYSIS ===");
        System.out.println("Collection name: " + collection.get("info").get("name").asText());

        JsonNode items = collection.get("item");
        for (JsonNode item : items) {
            System.out.println("\nFolder: " + item.get("name").asText());

            if (item.has("item")) {
                for (JsonNode request : item.get("item")) {
                    System.out.println("  Request: " + request.get("name").asText());
                    System.out.println("    Method: " + request.get("request").get("method").asText());
                    System.out.println("    URL: " + request.get("request").get("url"));

                    if (request.get("request").has("body")) {
                        System.out.println("    Body: " + request.get("request").get("body"));
                    }
                }
            }
        }
    }
}