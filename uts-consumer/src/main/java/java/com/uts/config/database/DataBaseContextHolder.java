package java.com.uts.config.database;

/**
 * Created by Administrator on 2018/3/6 0006.
 */
public class DataBaseContextHolder {

    public enum DataBaseType{
        UTS1("uts1"),
        UTS2("uts2");
        private String code;
        private DataBaseType(String code){
            this.code=code;
        }

        public String getCode(){
            return this.code;
        }

    }

    private static final  ThreadLocal<DataBaseType> contextHloder = new  ThreadLocal<DataBaseType>();

    public static void setDatabaseType(DataBaseType databaseType){
        if(databaseType==null){
            throw  new NullPointerException("DataBaseType null");
        }
        contextHloder.set(databaseType);
    }

    public static DataBaseType getDataBaseType(){
        return contextHloder.get()==null ? DataBaseType.UTS1:contextHloder.get();
    }

    public static void cloreDatabaseType(){
        contextHloder.remove();
    }
}
