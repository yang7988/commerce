package com.commerce.huayi.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
        String db = DataSourceContext.get();
        String remark = "======当前数据源为=====";
        LOGGER.warn(StringUtils.isBlank(db) ? remark + "默认数据源====chineseDatasource" : db);
        return db;
    }
}