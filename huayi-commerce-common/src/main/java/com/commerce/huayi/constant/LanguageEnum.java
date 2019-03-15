package com.commerce.huayi.constant;

public enum LanguageEnum {

    ZH_CN("chinese"),
    EN_US("english");

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
        for (LanguageEnum value : LanguageEnum.values()) {
            if(value.getLanguage().equals(language)) {
                return value;
            }
        }
        return null;
    }
}
