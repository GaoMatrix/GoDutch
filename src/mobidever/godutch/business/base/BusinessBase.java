package mobidever.godutch.business.base;

import android.content.Context;

public class BusinessBase {
    private Context mContext;
    
    public BusinessBase(Context context) {
        mContext = context;
    }
    
    protected String getString(int resID) {
        return mContext.getString(resID);
    }
    
    protected String getString(int resID,Object[] object) {
        return mContext.getString(resID, object);
    }
}
