package mobidever.godutch.database.sqlitedal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mobidever.godutch.database.base.SQLiteDALBase;
import mobidever.godutch.model.User;
import mobidever.godutch.utility.DateTools;

import java.util.Date;
import java.util.List;

public class SQLiteDALUser extends SQLiteDALBase{

    public SQLiteDALUser(Context context) {
        super(context);
    }
    
    public boolean insertUser(User user) {
        ContentValues contentValues = createParms(user);
        long newID = getDataBase().insert(getTableNameAndPK()[0], null, contentValues);
        user.setId((int)newID); //因为是引用类型所以，在这里修改也会修改外面的
        
        return newID > 0;
    }

    /**
     * 
     * @param condition 刪除的條件
     * @return
     */
    public boolean deleteUser(String condition) {
        return delete(getTableNameAndPK()[0], condition);
    }
    
    public boolean updateUser(String condition, User user) {
        ContentValues contentValues = createParms(user);
        return getDataBase().update(getTableNameAndPK()[0], contentValues, condition, null) > 0;
    }
    
    public List<User> getUser(String condition) {
        String sqlText = "Select * From User Where 1=1 " + condition;
        return getList(sqlText); // 这里使用了模板方法模式，钩子方法是findModel
    }

    private ContentValues createParms(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("UserName", user.getName());
        contentValues.put("CreateDate",
                DateTools.getFormatDateTime(user.getCreateDate(), "yyyy-MM-dd HH:mm:ss"));
        contentValues.put("State", user.getState());
        return contentValues;
    }

    @Override
    protected String[] getTableNameAndPK() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected Object findModel(Cursor cursor) {
         User user = new User();
        user.setId(cursor.getInt(cursor.getColumnIndex("id")));
        user.setName(cursor.getString(cursor.getColumnIndex("name")));
        Date createDate = DateTools.getDate(cursor.getString(cursor.getColumnIndex("createDate")), "yyyy-MM-dd HH:mm:ss");   
        user.setCreateDate(createDate);
        user.setState((cursor.getInt(cursor.getColumnIndex("state"))));
        
        return user;
    
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        StringBuilder createTableBuilder = new StringBuilder();

        createTableBuilder.append("        Create  TABLE User(");
        createTableBuilder .append("                [UserID] integer PRIMARY KEY AUTOINCREMENT NOT NULL");
        createTableBuilder.append("                ,[UserName] varchar(10) NOT NULL");
        createTableBuilder.append("                ,[CreateDate] datetime NOT NULL");
        createTableBuilder.append("                ,[State] integer NOT NULL");
        createTableBuilder.append("                )");

        database.execSQL(createTableBuilder.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase database) {
        // TODO Auto-generated method stub
        
    }

}
