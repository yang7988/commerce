package com.commerce.huayi;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MybatisCodeGen {
    public static void main(String[] args)  {
        try {
            String configPath = "E:/workspace/huayi-commerce/huayi-commerce-gen/src/main/resources/generatorConfig.xml";
            List<String> warnings = new ArrayList<>();
            boolean overwrite = false;
            File configFile = new File(configPath);
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configFile);
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            for (String warning : warnings) {
                System.out.println(warning);
            }
            myBatisGenerator.generate(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
