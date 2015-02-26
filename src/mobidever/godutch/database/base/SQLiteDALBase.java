
package mobidever.godutch.database.base;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mobidever.godutch.database.base.SQLiteHelper.SQLiteDataTable;

public abstract class SQLiteDALBase implements SQLiteDataTable{

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public SQLiteDALBase(Context context) {
        mContext = context;
    }

    protected Context getContext() {
        return mContext;
    }

    public SQLiteDatabase getDataBase() {
        if (null == mDatabase) {
            mDatabase = SQLiteHelper.getInstance(mContext).getWritableDatabase();
        }
        return mDatabase;
    }

    public void beginTransaction() {
        mDatabase.beginTransaction();
    }

    public void setTransactionSuccessful() {
        mDatabase.setTransactionSuccessful();
    }

    public void endTransaction() {
        mDatabase.endTransaction();
    }

    public int getCount(String condition) {
        String tableNameAndPK[] = getTableNameAndPK();
        Cursor cursor = execSql("select " + tableNameAndPK[0] + " from " + tableNameAndPK[1]
                + "where 1=1" + condition);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    protected abstract String[] getTableNameAndPK();

    public int getCount(String pk, String tableName, String condition) {
        Cursor cursor = execSql("Select " + pk + " From " + tableName + " Where 1=1 " + condition);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    protected Boolean delete(String tableName, String condition) {
        return getDataBase().delete(tableName, " 1=1 " + condition, null) >= 0;
    }

    protected List getList(String sql) {
        Cursor cursor = execSql(sql);
        return cursorToList(cursor);
    }

    /**
     * 查找数据，然后然会一个封装的实体类
     * @param cursor
     * @return
     */
    protected abstract Object findModel(Cursor cursor);

    /**
     * 游标转换位一个list
     * @param cursor
     * @return
     */
    protected List cursorToList(Cursor cursor) {
        List list = new ArrayList();
        while (cursor.moveToNext()) {
            Object Object = findModel(cursor);
            list.add(Object);
        }
        cursor.close();
        return list;
    }

    private Cursor execSql(String sql) {
        return getDataBase().rawQuery(sql, null);
    }

}
