package java.com.uts.utils;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

/**
 * Created by Administrator on 2018/3/6 0006.
 */
public class KeyUtil {

    public static String generatorUUID(){
        TimeBasedGenerator timeBasedGenerator = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
       return timeBasedGenerator.generate().toString();
    }
}
