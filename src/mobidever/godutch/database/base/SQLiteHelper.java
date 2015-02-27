
package mobidever.godutch.database.base;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import mobidever.godutch.utility.Reflection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static SQLiteDataBaseConfig sSqLiteDataBaseConfig;
    private static SQLiteOpenHelper sInstance;
    private static Context mContext;
    private Reflection mReflection;
    
    public interface SQLiteDataTable{
        public void onCreate(SQLiteDatabase database);
        public void onUpgrade(SQLiteDatabase database);
    }

    private SQLiteHelper(Context context) {
        super(context, sSqLiteDataBaseConfig.getDataBaseName(), null, sSqLiteDataBaseConfig
                .getVersion());
        mContext = context;
    }
    
    public static SQLiteOpenHelper getInstance(Context context) {
        if (null == sInstance) {
            sSqLiteDataBaseConfig = SQLiteDataBaseConfig.getInstance(context);
            sInstance = new SQLiteHelper(context);
            mContext = context;
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ArrayList<String> arrayList = sSqLiteDataBaseConfig.getTables();
        mReflection = new Reflection();
        for (int i = 0; i < arrayList.size(); i++) {
            try {
                SQLiteDataTable sqLiteDataTable = (SQLiteDataTable) mReflection.newInstance(arrayList.get(i), 
                        new Object[]{mContext}, new Class[]{Context.class});
                sqLiteDataTable.onCreate(db);
            } catch (Exception e) {
                e.printStackTrace();
            } 
        }
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

}
