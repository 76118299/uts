package java.com.uts.config.database;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 加载mybatis SessionFactory
 * Created by Administrator on 2018/3/6 0006.
 */
@Configuration
@EnableTransactionManagement
@AutoConfigureAfter(DruidDataSourceConfig.class)
public class MyBatisConfiguration {
    @Resource(name="uts1DataSource")
    private DataSource dataSource1;
    @Resource(name="uts2DataSource")
    private DataSource dataSource2;

    @Bean(name="DynamicDataSource")
    public DynamicDataSource routingDataSourceProxy(){
        Map<Object,Object> trageDatasource = new HashMap<Object,Object>();
        trageDatasource.put(DataBaseContextHolder.DataBaseType.UTS1,dataSource1);
        trageDatasource.put(DataBaseContextHolder.DataBaseType.UTS2,dataSource2);
        DynamicDataSource proxy = new DynamicDataSource();
        proxy.setTargetDataSources(trageDatasource);
        proxy.setDefaultTargetDataSource(dataSource1);

        return  proxy;
    }
    @Bean(name="sessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean( DynamicDataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource);
        //加载XML Mapper文件
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resourcePatternResolver.getResources("classpath:com/uts/mapping/*.xml"));
        SqlSessionFactory sqlSessionFactory = bean.getObject();
        sqlSessionFactory.getConfiguration().setCacheEnabled(true);

        return  sqlSessionFactory;

    }
    @Bean
    public PlatformTransactionManager transactionManager(DynamicDataSource dynamicDataSource){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dynamicDataSource);

        return transactionManager;

    }


}
