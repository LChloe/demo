package com.example.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

/**
 * 可去除
 * 使用此配置会导致基础service调用repository报找不到bean
 */
//@Configuration
//@EnableJpaRepositories(
//        basePackages= { "com.example.demo.Search.config" })
//@EnableTransactionManagement
//@EnableJpaAuditing
public class DataSourceConfig {

//    @Autowired
//    private Environment environment;
//
//    @Bean(name = "dataSource")
//    @Qualifier("dataSource")
//    @Primary
//    public HikariDataSource dataSource() {
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setDriverClassName(environment.getRequiredProperty("spring.datasource.driver-class-name"));
//        dataSource.setJdbcUrl(environment.getRequiredProperty("spring.datasource.url"));
//        dataSource.setUsername(environment.getRequiredProperty("spring.datasource.username"));
//        dataSource.setPassword(environment.getRequiredProperty("spring.datasource.password"));
//
//        return dataSource;
//    }
//
//    @Bean(name = "entityManagerFactory")
//    @Primary
//    public EntityManagerFactory entityManagerFactory() {
//
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setDatabase(Database.MYSQL);
//        vendorAdapter.setShowSql(false);
//        vendorAdapter.setGenerateDdl(false);
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(vendorAdapter);
//        factory.setJpaProperties(jpaProperties());
//        factory.setPackagesToScan("com.self.core.entity");
//        factory.setDataSource(dataSource());
//        factory.afterPropertiesSet();
//        return factory.getObject();
//    }
//
//    @SuppressWarnings("serial")
//    private Properties jpaProperties() {
//        return new Properties() {
//            {
//                setProperty("hibernate.physical_naming_strategy",
//                        "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
//            }
//        };
//    }
//
//    @Bean(name = "transactionManager")
//    @Primary
//    public PlatformTransactionManager transactionManager() {
//        JpaTransactionManager txManager = new JpaTransactionManager();
//        txManager.setEntityManagerFactory(entityManagerFactory());
//        return txManager;
//    }
}