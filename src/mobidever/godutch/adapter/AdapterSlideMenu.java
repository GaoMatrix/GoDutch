
package mobidever.godutch.adapter;

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

public class AdapterSlideMenu extends AdapterBase {
    
    private class Holder {
        ImageView ivIcon;
        TextView tvName;
    }

    public AdapterSlideMenu(Context context, List list) {
        super(context, list);
        // TODO Auto-generated constructor stub
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (null == convertView) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.main_body_item, null);
            holder = new Holder();
            holder.ivIcon = (ImageView) convertView.findViewById(R.id.ivIcon);
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.ivIcon.setImageResource(mImageInteger[position]);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(80, 80);
        holder.ivIcon.setLayoutParams(layoutParams);
        holder.ivIcon.setScaleType(ImageView.ScaleType.FIT_XY);

        holder.tvName.setText(mImageString[position]);
        return convertView;
    }

}
