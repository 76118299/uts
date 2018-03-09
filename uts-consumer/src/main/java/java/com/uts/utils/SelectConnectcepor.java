package java.com.uts.utils;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.com.uts.config.database.DataBaseContextHolder;
import java.com.uts.entity.BaseEntity;

/**
 * Created by Administrator on 2018/3/7 0007.
 */
@Aspect
@Component
public class SelectConnectcepor implements Ordered {

    private static final String  PREFIX = "uts";
    @Around("@annotation(SelectConnect)")
    public Object proceed(ProceedingJoinPoint pjp,SelectConnect selectConnect) throws Throwable {
       String currentDatabaseName="";
        if(!StringUtils.isBlank(selectConnect.name())){
            currentDatabaseName = selectConnect.name();
        }else {
            BaseEntity baseEntity = (BaseEntity) pjp.getArgs()[0];
           String uuid= baseEntity.getId();
            Pair<Integer, Integer> databaseAndTableNum = SelectDatabaseUtils.getDatabaseAndTableNum(uuid);
            currentDatabaseName = PREFIX+databaseAndTableNum.getObject1();
        }
        for(DataBaseContextHolder.DataBaseType type: DataBaseContextHolder.DataBaseType.values()) {
            if(!StringUtils.isBlank(currentDatabaseName)){
              String typeCode=  type.getCode();
                if(typeCode.equals(currentDatabaseName)){
                    DataBaseContextHolder.setDatabaseType(type);
                }
            }
        }
        Object proceed = null;
        try {
            proceed = pjp.proceed();
        }
     finally {
            DataBaseContextHolder.cloreDatabaseType();
        }

        return  proceed;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
