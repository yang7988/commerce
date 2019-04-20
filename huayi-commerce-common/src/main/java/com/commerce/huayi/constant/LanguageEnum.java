package com.commerce.huayi.constant;

import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Stream;

public enum LanguageEnum {

    ZH_CN("chinese", "chineseDataSource"),
    EN_US("english", "englishDataSource"),
    DE_DE("german", "chineseDataSource"),
    FR_FR("french", "chineseDataSource"),
    JP_JP("japanese", "chineseDataSource");

    private String language;

    private String datasource;


    LanguageEnum(String language) {
        this.language = language;
    }

    LanguageEnum(String language, String datasource) {
        this.language = language;
        this.datasource = datasource;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    public static LanguageEnum enums(String language) {
        Stream<LanguageEnum> stream = Stream.of(LanguageEnum.values());
        Optional<LanguageEnum> optional = stream.filter(enumItem -> enumItem.getLanguage().equals(language)).findFirst();
        return optional.orElse(null);
    }

    public static void main(String[] args) {
        System.out.println(enums("french"));
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", LanguageEnum.class.getSimpleName() + "[", "]")
                .add("language='" + language + "'")
                .toString();
    }
}
