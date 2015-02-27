package mobidever.godutch.business;

import android.content.Context;

import mobidever.godutch.business.base.BusinessBase;
import mobidever.godutch.database.sqlitedal.SQLiteDALUser;
import mobidever.godutch.model.User;

import java.util.ArrayList;
import java.util.List;

public class BusinessUser extends BusinessBase{

    private SQLiteDALUser mSqLiteDALUser;
    
    public BusinessUser(Context context) {
        super(context);
        mSqLiteDALUser = new SQLiteDALUser(context);
    }
    
    public boolean insertUser(User user) {
        boolean result = mSqLiteDALUser.insertUser(user);
        return result;
    }
    
    public boolean deleteUserByID(int userID) {
        String condition = " And id = " + userID;
        boolean result = mSqLiteDALUser.deleteUser(condition);
        return result;
    }
    
    public boolean updateUserByUserID(User user) {
        String condition = " And id = " + user.getId();
        boolean result = mSqLiteDALUser.updateUser(condition, user);
        return result;
    }

    /**
     * 私有方法，主要是业务层自己内部调用
     * @param condition
     * @return
     */
    private List<User> getUser(String condition) {
        return mSqLiteDALUser.getUser(condition);
    }
    
    public User getUserByID(int userID) {
        List<User> list = mSqLiteDALUser.getUser(" And id = " + userID);
        if (list.size() == 1) {
            return list.get(0);
        } else {
            return null;
        }
    }
    
    public List<User> getUserListByID(String userID[]) {
        List<User> list = new ArrayList<User>();
        for (int i = 0; i < userID.length; i++) {
            list.add(getUserByID(Integer.valueOf(userID[i])));
        }
        
        return list;
    }
    
    public List<User> getNotHideUser() {
        return mSqLiteDALUser.getUser(" And state = 1 ");
    }
    
    
    
    
    
    
    
    
}
