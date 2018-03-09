package java.com.uts.config.database;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import javax.validation.Valid;
import java.sql.SQLException;

/**
 * 加载多数据源
 * Created by Administrator on 2018/3/5 0005.
 */
@Configuration
public class DruidDataSourceConfig {
    private static Logger logger = LoggerFactory.getLogger(DruidDataSourceConfig.class);
    @Value("${datasource.type}")
    private Class<? extends DataSource> dataSourceType;
    @Bean("uts1DataSource")
    @ConfigurationProperties(prefix = "druid.uts1")
    @Primary
    public DataSource uts1DataSource(){
        DataSource uts1DataSource = DataSourceBuilder.create().type(dataSourceType).build();
        logger.info("---------uts1----",uts1DataSource);
        return uts1DataSource;
    }
    @Bean("uts2DataSource")
    @ConfigurationProperties(prefix = "druid.uts2")
    public DataSource uts2DataSource(){
        DataSource uts2DataSource = DataSourceBuilder.create().type(dataSourceType).build();
        logger.info("---------uts2----",uts2DataSource);
        return uts2DataSource;
    }

    /**
     * 开启数据源监控台
     * @return
     */

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


}
