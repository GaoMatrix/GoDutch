
package mobidever.godutch.database.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static SQLiteDataBaseConfig sSqLiteDataBaseConfig;
    private static SQLiteOpenHelper sInstance;
    private Context mContext;
    
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
            sInstance = new SQLiteHelper(context);
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

}
