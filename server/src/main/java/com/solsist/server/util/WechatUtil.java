package com.solsist.server.util;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class WechatUtil {

    private static String APPID;
    private static String SECRET;
    private static String GRANT_TYPE;
    static private Properties properties = new Properties();

    static {
        try {
            InputStream inputStream = WechatUtil.class.getClassLoader().getResourceAsStream("application.properties");
            properties.load(inputStream);
            APPID = properties.getProperty("wx.appid");
            SECRET = properties.getProperty("wx.secret");
            GRANT_TYPE = properties.getProperty("wx.grant_type");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Data
    class Code2SessionBody {
        private String openid;
        private String session_key;
//        private String unionid;
//        private String errcode;
//        private String errmsg;
    }

    public static String getOpenId(String code) {
        try {
            URL url = new URL("https://api.weixin.qq.com/sns/jscode2session?appid=" + APPID + "&secret=" + SECRET + "&js_code="
                    + code + "&grant_type=" + GRANT_TYPE);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            Code2SessionBody body = JSONObject.parseObject(response.toString(), Code2SessionBody.class);
            System.out.println(body);
            connection.disconnect();
            return body.getOpenid();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getOpenId("0b1JeA000AlPbR1aA9300I6mWa4JeA0S"));
    }
}
