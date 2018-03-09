package java.com.uts.config.database;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;

/**
 * Created by Administrator on 2018/3/5 0005.
 */
@Configurable
@AutoConfigureAfter(MyBatisConfiguration.class)
public class MybatisMapperScanerConfig {
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.uts.mapper");
        return  mapperScannerConfigurer;
    }
}
