
package mobidever.godutch.adapter;

import android.R.color;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import mobidever.godutch.R;
import mobidever.godutch.adapter.base.AdapterBase;
import mobidever.godutch.business.BusinessUser;
import mobidever.godutch.model.User;

public class AdapterUser extends AdapterBase {
    private class Holder {
        ImageView ivUserIcon;
        TextView tvUserName;
    }

    private Context mContext;

    public AdapterUser(Context context)
    {
        super(context, null);
        BusinessUser businessUser = new BusinessUser(context);
        List list = businessUser.getNotHideUser();
        setList(list);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (null == convertView) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.user_list_item, null);
            holder = new Holder();
            holder.ivUserIcon = (ImageView) convertView.findViewById(R.id.ivUserIcon);
            holder.tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        
        User user = (User) getList().get(position);
        holder.ivUserIcon.setImageResource(R.drawable.user_big_icon);
        holder.tvUserName.setText(user.getName());
        return convertView;
    }

}
