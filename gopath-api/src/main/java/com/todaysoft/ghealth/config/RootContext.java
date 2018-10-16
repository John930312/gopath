package com.todaysoft.ghealth.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author: xjw
 * @Date: 2018/10/15 11:13
 */

@Configuration
@MapperScan("com.todaysoft.ghealth.mybatis.mapper")
@EnableTransactionManagement
public class RootContext
{
    
    @Autowired
    private Environment environment;
    
    @Bean(name = "characterEncodingFilter")
    public CharacterEncodingFilter getCharacterEncodingFilter()
    {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("utf-8");
        return filter;
    }
    
    @Bean(name = "dataSource")
    public DataSource getDataSource() throws SQLException
    {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("datasource.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("datasource.url"));
        dataSource.setUsername(environment.getRequiredProperty("datasource.username"));
        dataSource.setPassword(environment.getRequiredProperty("datasource.password"));
        dataSource.setFilters("stat,config");
        
        dataSource.setInitialSize(environment.getRequiredProperty("datasource.initial", int.class));
        dataSource.setMaxActive(environment.getRequiredProperty("datasource.max.active", int.class));
        dataSource.setMinIdle(environment.getRequiredProperty("datasource.min.idle", int.class));
        dataSource.setMaxWait(environment.getRequiredProperty("datasource.max.wait", long.class));
        
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(30000);
        
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeoutMillis(1800000);
        
        dataSource.setValidationQuery("SELECT 'x'");
        dataSource.setTestOnReturn(false);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestWhileIdle(true);
        
        Properties properties = new Properties();
        properties.setProperty("config.decrypt", "true");
        properties.setProperty("config.decrypt.key", environment.getRequiredProperty("datasource.public.key"));
        dataSource.setConnectProperties(properties);
        return dataSource;
    }
    
    @Bean
    public DataSourceTransactionManager transactionManager() throws SQLException
    {
        return new DataSourceTransactionManager(getDataSource());
    }
    
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception
    {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(getDataSource());
        return sessionFactory.getObject();
    }
    
}
