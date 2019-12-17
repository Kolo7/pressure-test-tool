package com.xgsdk.sdkserver.antiaddiction.loader;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.xgsdk.sdkserver.antiaddiction.info.AntiAddictionLoginInfo;
import com.xgsdk.sdkserver.antiaddiction.info.AntiAddictionUserPayInfo;
import com.xgsdk.sdkserver.antiaddiction.request.Requester;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Random;

@Component
public class DeskLoader implements CommandLineRunner {
    @Autowired
    private Requester requester;
    @Override
    public void run(String... args) throws Exception {
        Options options = new Options();
        options.addOption("opt", true, "option");
        options.addOption("url", true, "url");
        options.addOption("port", true, "port");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        if (!cmd.hasOption("opt")){
            System.out.println("缺少参数-opt");
            return;
        }
        if(!cmd.hasOption("url")){
            System.out.println("缺少参数-url");
            return;
        }
        if(!cmd.hasOption("port")){
            System.out.println("缺少参数-port");
        }
        String opt = cmd.getOptionValue("opt");
        String port = cmd.getOptionValue("port");
        String url = cmd.getOptionValue("url");
        Random random = new Random();
        int num = random.nextInt(100);
        String file = opt + num + ".json";
        InputStream inputStream = new ClassPathResource(file).getInputStream();
        if (opt.equals("login")){
            readStreamLogin(inputStream,url,port);
        }else if(opt.equals("pay")){
            readStreamPay(inputStream,url,port);
        }
    }

    public void readStreamLogin(InputStream inputStream,String url, String port) {
        try {

            JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
            Gson gson = new GsonBuilder().create();

            // Read file in stream mode
            reader.beginArray();
            int n = 0;
            Long start = System.currentTimeMillis();
            while (reader.hasNext()) {
                // Read data into object model
                AntiAddictionLoginInfo  info = gson.fromJson(reader, AntiAddictionLoginInfo.class);
                requester.requestLogin(info,url,port);
                n++;
            }
            System.out.println("login speed time:"+String.valueOf(System.currentTimeMillis()-start)+";"+"count:"+n);
            reader.close();
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void readStreamPay(InputStream inputStream,String url, String port) {
        try {
            JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
            Gson gson = new GsonBuilder().create();
            int n=0;
            // Read file in stream mode
            reader.beginArray();
            Long start = System.currentTimeMillis();
            while (reader.hasNext()) {
                // Read data into object model
                AntiAddictionUserPayInfo info = gson.fromJson(reader, AntiAddictionUserPayInfo.class);
                requester.requestPay(info,url,port);
                n++;
            }
            System.out.println("login speed time:"+String.valueOf(System.currentTimeMillis()-start)+";"+"count:"+n);
            reader.close();
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
