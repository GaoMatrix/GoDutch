package mobidever.godutch.adapter.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class AdapterBase extends BaseAdapter{
    private List mList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public AdapterBase(Context context, List list) {
        mContext = context;
        mList = list;
        mLayoutInflater = LayoutInflater.from(mContext);
    }
    
    public LayoutInflater getLayoutInflater() {
        return mLayoutInflater;
    }
    
    public Context getContext() {
        return mContext;
    }
    
    public List getList() {
        return mList;
    }
    
    public void setList(List list) {
        mList = list;
    }
    
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 这个不封装，跟业务无关的能封装的封装在这里，
     * 跟业务有关的封装不了，谁使用谁去使用实现这个方法。
     */
    /*@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        return null;
    }*/

}
