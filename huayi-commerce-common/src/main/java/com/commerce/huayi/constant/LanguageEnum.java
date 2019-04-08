package com.commerce.huayi.constant;

import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Stream;

public enum LanguageEnum {

    ZH_CN("chinese"),
    EN_US("english"),
    DE_DE("german"),
    FR_FR("french"),
    JP_JP("japanese");

    private String language;

    LanguageEnum(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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
