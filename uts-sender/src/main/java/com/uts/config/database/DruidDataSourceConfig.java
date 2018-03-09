package com.uts.config.database;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 配置数据库连接和事物管理
 * Created by Administrator on 2018/3/5 0005.
 */
@Configuration
@EnableTransactionManagement
public class DruidDataSourceConfig {
    private static Logger logger = LoggerFactory.getLogger(DruidDataSourceConfig.class);

    @Autowired
    private DruidDataSourceSettings druidDataSourceSettings;

    public static String DRIVER_CLASSNAME ;
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigure(){
        return new PropertySourcesPlaceholderConfigurer();
    }
    @Bean
    public ServletRegistrationBean druidServlet(){
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.addUrlMappings("/druid/*");
        reg.addInitParameter("allow", "127.0.0.1");
        reg.addInitParameter("loginUsername", "bhz");
        reg.addInitParameter("loginPassword", "bhz");
        logger.info(" druid console manager init : {} ", reg);
        return reg;
    }
    @Bean
    public  FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico, /druid/*");
        logger.info(" druid filter register : {} ", filterRegistrationBean);
        return filterRegistrationBean;
    }


    @Bean
    public DataSource dataSource() throws SQLException {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(druidDataSourceSettings.getDriverClassName());
        DRIVER_CLASSNAME = druidDataSourceSettings.getDriverClassName();
        ds.setUrl(druidDataSourceSettings.getUrl());
        ds.setUsername(druidDataSourceSettings.getUsername());
        ds.setPassword(druidDataSourceSettings.getPassword());
        ds.setInitialSize(druidDataSourceSettings.getInitialSize());
        ds.setMinIdle(druidDataSourceSettings.getMinIdle());
        ds.setMaxIdle(druidDataSourceSettings.getMaxIdle());
        ds.setMaxActive(druidDataSourceSettings.getMaxActive());
        ds.setTimeBetweenEvictionRunsMillis(druidDataSourceSettings.getTimeBetweenEvictionRunsMillis());
        ds.setMinEvictableIdleTimeMillis(druidDataSourceSettings.getMinEvictableIdleTimeMillis());
        ds.setValidationQuery(druidDataSourceSettings.getValidationQuery());
        ds.setTestWhileIdle(druidDataSourceSettings.isTestWhileIdle());
        ds.setTestOnBorrow(druidDataSourceSettings.isTestOnBorrow());
        ds.setTestOnReturn(druidDataSourceSettings.isTestOnReturn());
        ds.setPoolPreparedStatements(druidDataSourceSettings.isPoolPreparedStatements());
        ds.setMaxPoolPreparedStatementPerConnectionSize(druidDataSourceSettings.getMaxPoolPreparedStatementPerConnectionSize());
        ds.setFilters(druidDataSourceSettings.getFilters());
        ds.setConnectionProperties(druidDataSourceSettings.getConnectionProperties());

        //proxyFilters ===> 有问题
//		WallFilter wallFilter = new WallFilter();
//		WallConfig wallConfig = new WallConfig();
//		wallConfig.setMultiStatementAllow(true);
//		wallFilter.setConfig(wallConfig);
//		List<Filter> wallFilterList = new ArrayList<Filter>();
//		wallFilterList.add(wallFilter);
//		ds.setProxyFilters(wallFilterList);
        logger.info(" druid datasource config : {} ", ds);
        return ds;
    }
    @Bean
    public  PlatformTransactionManager transactionManager() throws SQLException {
        DataSourceTransactionManager txManager = new DataSourceTransactionManager();
        txManager.setDataSource(dataSource());
        return txManager;
    }



}
