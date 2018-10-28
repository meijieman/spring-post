package com.major.springpost.controller;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;

public class LoginPostControllerTest {


    private final Logger log = LoggerFactory.getLogger(getClass());

    @Test
    public void login() throws Exception {
        String url = "http://localhost:8080/post";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.add("aaa", "111");
        String body = "{\"usr\": \"major\",\t\"pwd\": 123456}";
        HttpEntity<String> formEntity = new HttpEntity<>(body, headers);
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(url, HttpMethod.POST, formEntity , String.class);

        assert resp.getStatusCodeValue() == 200:" code " + resp.getStatusCodeValue();

        String respBody = resp.getBody();
        log.info("请求结果 " + respBody);
    }

    @Test
    public void upload() throws Exception {
        // FIXME: 2018/10/28 上传文件不能设置 header ？
        String url = "http://localhost:8080/upload";
        String path = "D:\\tmp\\2210245jtrdd04djir0rrr.jpg";
        FileSystemResource resource = new FileSystemResource(new File(path));
//        MultiValueMap params = new LinkedMultiValueMap();
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>(); // 需要指明泛型类型
        params.add("jarFile", resource);
        params.add("filename", "test.txt");
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(params);
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(url, HttpMethod.POST, entity, String.class);

        assert resp.getStatusCodeValue() == 200:" code " + resp.getStatusCodeValue();

        String body = resp.getBody();
        log.info("文件上传成功 " + body);
    }

}