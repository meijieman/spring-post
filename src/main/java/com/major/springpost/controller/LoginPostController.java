package com.major.springpost.controller;

import com.google.gson.JsonObject;
import com.major.springpost.util.GSON;
import com.major.springpost.util.StreamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

@RestController("/")
public class LoginPostController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 接收 post 上来的 string
     * @param req
     * @param resp
     * @throws UnsupportedEncodingException
     */
    @PostMapping("/post")
    public void login(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        log.info("收到 post 请求");

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        Map<String, String[]> params = req.getParameterMap();
        log.info("params " + GSON.toJson(params));
        String contentType = req.getContentType();

        String aaa = req.getHeader("aaa");
        String bbb = req.getHeader("bbb");
        log.info("headers aaa " + aaa + ", bbb " + bbb);
        log.info("headers contentType " + contentType);

        try {
            JsonObject jsonObject = GSON.parseJsonObject(new InputStreamReader(req.getInputStream()));
            log.warn("data " + jsonObject);
            PrintWriter pw = resp.getWriter();
            if ("major".equals(jsonObject.get("usr").getAsString())
                    && "123456".equals(jsonObject.get("pwd").getAsString())) {
                pw.write("login success");
            } else {
                pw.write("login  failure");
            }

            pw.close(); // 一定要调用 close
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/upload")
    public void upload(HttpServletRequest req, HttpServletResponse resp, MultipartFile jarFile){
        String contentType = req.getContentType();
        log.info("contentType " + contentType);
        Map<String, String[]> params = req.getParameterMap();
        log.info("params " + GSON.toJson(params));

        String original = jarFile.getOriginalFilename();
        File file = new File("d:\\tmp", "server-" + original);
        if (file.exists()) {
            log.warn("文件已存在，删除 " + file.delete());
        }
        try {
            InputStream is = jarFile.getInputStream();
            FileOutputStream fos = new FileOutputStream(file);
            StreamUtil.readStream(is, fos);
            log.info("保存文件成功 " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
