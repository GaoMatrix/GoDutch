package mobidever.godutch.database.base;

import java.util.ArrayList;

import mobidever.godutch.R;

import android.content.Context;



public class SQLiteDataBaseConfig {
    private static final String DATABASENAME  = "GoDutchDataBase";
    private static final int VERTION = 1;
    private static SQLiteDataBaseConfig sInstanc;
    private static Context mContext;
    

    private SQLiteDataBaseConfig() {
        
    }
    
    public static SQLiteDataBaseConfig getInstance(Context context) {
        if (null == sInstanc) {
            sInstanc = new SQLiteDataBaseConfig();
            mContext = context;
        }
        return sInstanc;
    }
    
    public String getDataBaseName() {
        return DATABASENAME;
    }
    
    public int getVersion(){
        return VERTION;
    }
    
    /**
     * 动态用反射加载一个类，生成这个类的实力，并且调用里面的方法，
     * 而不用硬编码的方式。
     * @return
     */
    public ArrayList<String> getTables() {
        ArrayList<String> arrayList = new ArrayList<String>();
        String[] sqLiteDALClassName = mContext.getResources().getStringArray(R.array.SQLiteDALClassName);
        String packagePath = mContext.getPackageName() + ".database.sqlitedal.";
        for (int i = 0; i < sqLiteDALClassName.length; i++) {
            arrayList.add(packagePath + sqLiteDALClassName[i]);
        }
        return arrayList;
    }
    
    
    
    
    
    
    
}
