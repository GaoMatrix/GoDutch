package mobidever.godutch.database.base;

import java.util.ArrayList;



public class SQLiteDataBaseConfig {
    private static final String DATABASENAME  = "GoDutchDataBase";
    private static final int VERTION = 1;
    private static SQLiteDataBaseConfig sInstanc;
    

    private SQLiteDataBaseConfig() {
        
    }
    
    public static SQLiteDataBaseConfig getInstance() {
        if (null == sInstanc) {
            sInstanc = new SQLiteDataBaseConfig();
        }
        return sInstanc;
    }
    
    public String getDataBaseName() {
        return DATABASENAME;
    }
    
    public int getVersion(){
        return VERTION;
    }
    
    public ArrayList<String> getTables() {
        ArrayList<String> arrayList = new ArrayList<String>();
        
        return arrayList;
    }
    
    
    
    
    
    
    
}
