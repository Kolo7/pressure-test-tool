package com.xgsdk.sdkserver.antiaddiction.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xgsdk.sdkserver.antiaddiction.info.AntiAddictionLoginInfo;
import com.xgsdk.sdkserver.antiaddiction.info.AntiAddictionUserPayInfo;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

@Component
public class Requester {
    private static CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    @Value("${adti-addiction.request-url}")
    private String url;
    @Value("${adti-addiction.port}")
    private String port;
    @Value("${adti-addiction.loginParameter}")
    private String loginParameter;
    @Value("${adti-addiction.payParameter}")
    private String payParameter;
    @Value("${adti-addiction.key}")
    private String key;
    @Value("${adti-addiction.loginJsonKey}")
    private String loginJsonKey;
    @Value("${adti-addiction.payJsonKey}")
    private String payJsonKey;
    @Async("myTaskAsyncPool")
    public void requestLogin(AntiAddictionLoginInfo info,String url, String port){
        request(getURL(url, port, loginParameter), JSONObject.toJSONString(info), 0);
    }
    @Async("myTaskAsyncPool")
    public void requestPay(AntiAddictionUserPayInfo info,String url, String port){
        request(getURL(url, port, payParameter), JSONObject.toJSONString(info),0);
    }

    public void request(String url,String json, int fail){
        AntiAddictionLoginInfo ingo = JSON.parseObject(json, AntiAddictionLoginInfo.class);
        HttpPost requset = new HttpPost(url);
        requset.addHeader("Content-Type", "application/json;charset=UTF-8");
        StringEntity stringEntity = new StringEntity(json,"UTF-8");
        stringEntity.setContentEncoding("UTF-8");
        requset.setEntity(stringEntity);
        CloseableHttpResponse response = null;
        try{
            response = httpClient.execute(requset);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            if(StringUtils.isEmpty(requset))
                throw new NullPointerException();
/*            if (!result.startsWith("0")) {
                System.out.println(result);
                System.out.println(json);
            }*/
        }catch (NullPointerException e ){
            e.printStackTrace();
        }catch (Exception e){
            // 最多尝试请求十次
            if(fail <= 10)
                request(url, json, fail+1);
            else
                throw new RuntimeException();
        }
    }

    private String getURL(String url, String port, String parameter){
        return "http://"+url+":"+port+"/"+parameter;
    }
}
