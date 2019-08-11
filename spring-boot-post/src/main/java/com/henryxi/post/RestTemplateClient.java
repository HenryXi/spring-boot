package com.henryxi.post;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class RestTemplateClient {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("luck", "luck-dog");
        ResponseEntity<Map> result = restTemplate.postForEntity("http://192.168.0.111:8888/nginx-post", params, Map.class);
        System.out.println(result.getBody().toString());
    }
}
