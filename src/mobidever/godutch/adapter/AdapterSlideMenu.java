
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
import mobidever.godutch.controls.SlideMenuItem;

public class AdapterSlideMenu extends AdapterBase {
    
    private class Holder {
        TextView tvMenuName;
    }

    public AdapterSlideMenu(Context context, List list) {
        super(context, list);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (null == convertView) {
            convertView = getLayoutInflater().inflate(R.layout.slidemenu_list_item, null);
            holder = new Holder();
            holder.tvMenuName = (TextView) convertView.findViewById(R.id.tvName);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        
        SlideMenuItem item = (SlideMenuItem) getmList().get(position);
        holder.tvMenuName.setText(item.getTitle());
        return convertView;
    }

}
