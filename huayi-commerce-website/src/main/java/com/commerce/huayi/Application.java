package com.commerce.huayi;

import com.alibaba.druid.pool.DruidDataSource;
import com.commerce.huayi.constant.LanguageEnum;
import com.commerce.huayi.service.impl.DynamicDataSource;
import com.google.common.collect.Sets;
import org.hibernate.validator.HibernateValidator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.commerce.huayi"})
@MapperScan(basePackages = "com.commerce.huayi.mapper")
@EnableSwagger2
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean(name = {"chineseDataSource"}, destroyMethod = "close", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource chineseDataSource() {
        return new DruidDataSource();
    }

    @Bean(name = {"englishDataSource"}, destroyMethod = "close", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource.english")
    public DataSource englishDataSource() {
        return new DruidDataSource();
    }

    @Primary
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(chineseDataSource());
        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap<>();
        dsMap.put(LanguageEnum.ZH_CN.getDatasource(), chineseDataSource());
        dsMap.put(LanguageEnum.EN_US.getDatasource(), englishDataSource());
        dsMap.put(LanguageEnum.DE_DE.getDatasource(), chineseDataSource());
        dsMap.put(LanguageEnum.FR_FR.getDatasource(), chineseDataSource());
        dsMap.put(LanguageEnum.JP_JP.getDatasource(), chineseDataSource());

        dynamicDataSource.setTargetDataSources(dsMap);
        return dynamicDataSource;
    }



    @Bean
    public Validator getValidatorFactory() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).configure().failFast(true)
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(Sets.newHashSet("application/json"))
                .consumes(Sets.newHashSet("application/json"))
                .protocols(Sets.newHashSet("http", "https"))
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.commerce.huayi.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("huayi-commerce文档RESTful-APIs")
                .description("")
                .termsOfServiceUrl("https://github.com/yang7988/huayi-commerce.git")
                .contact("徐阳、上官小文")
                .version("1.0")
                .build();
    }

}
