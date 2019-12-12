package com.xgsdk.sdkserver.antiaddiction.loader;

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

@Component
public class DeskLoader implements CommandLineRunner {
    @Autowired
    private Requester requester;
    @Override
    public void run(String... args) throws Exception {
        Options options = new Options();
        options.addOption("opt", true, "option");
        options.addOption("file", true, "file");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        if (!cmd.hasOption("opt")){
            System.out.println("缺少参数-opt");
            return;
        }
        if (!cmd.hasOption("file")){
            System.out.println("缺少参数-file");
            return;
        }
        String opt = cmd.getOptionValue("opt");
        String file = cmd.getOptionValue("file");
        File jsonFile = new ClassPathResource(file).getFile();
        if (opt.equals("login")){
            readStreamLogin(jsonFile);
        }else if(opt.equals("pay")){
            readStreamPay(jsonFile);
        }
    }

    public void readStreamLogin(File jsonFile) {
        try {

            JsonReader reader = new JsonReader(new InputStreamReader(new FileInputStream(jsonFile), "UTF-8"));
            Gson gson = new GsonBuilder().create();

            // Read file in stream mode
            reader.beginArray();
            Long start = System.currentTimeMillis();
            while (reader.hasNext()) {
                // Read data into object model
                AntiAddictionLoginInfo  info = gson.fromJson(reader, AntiAddictionLoginInfo.class);
                requester.requestLogin(info);
            }
            System.out.println("login speed time:"+String.valueOf(System.currentTimeMillis()-start));
            reader.close();
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void readStreamPay(File jsonFile) {
        try {
            JsonReader reader = new JsonReader(new InputStreamReader(new FileInputStream(jsonFile), "UTF-8"));
            Gson gson = new GsonBuilder().create();

            // Read file in stream mode
            reader.beginArray();
            Long start = System.currentTimeMillis();
            while (reader.hasNext()) {
                // Read data into object model
                AntiAddictionUserPayInfo info = gson.fromJson(reader, AntiAddictionUserPayInfo.class);
                requester.requestPay(info);
            }
            System.out.println("pay speed time:"+String.valueOf(System.currentTimeMillis()-start));
            reader.close();
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
