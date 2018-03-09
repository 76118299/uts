package java.com.uts.utils;

/**
 * Created by Administrator on 2018/3/7 0007.
 */
public class SelectDatabaseUtils {
    public static Pair<Integer,Integer> getDatabaseAndTableNum(String uuid){
        int hashCode = Math.abs(uuid.hashCode());
        int dataBaseNum = (hashCode/10)%2+1;
        int selectTableNum = hashCode%2;
        Pair<Integer,Integer> p = new Pair<Integer,Integer>(dataBaseNum,selectTableNum);
        return p;
    }
}
