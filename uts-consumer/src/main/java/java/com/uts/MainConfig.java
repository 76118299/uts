package java.com.uts;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Administrator on 2018/3/5 0005.
 */
@Configurable
@MapperScan("com.uts.mapper")
@ComponentScan(basePackages = {"com.uts.*","com.uts.config.*"})
public class MainConfig {
}
