package com.lambda.bigtask.config;

import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class MyConfig {
    @Bean
    @Primary
    public DataSourceTransactionManager transactionManager(DataSource dataSource, TransactionManagerCustomizers transactionManagerCustomizers) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        if (transactionManagerCustomizers != null) {
            transactionManagerCustomizers.customize(transactionManager);
        }
        return transactionManager;
    }
}
