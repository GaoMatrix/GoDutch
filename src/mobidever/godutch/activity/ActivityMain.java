
package mobidever.godutch.activity;

import android.os.Bundle;
import android.widget.GridView;

import mobidever.godutch.R;
import mobidever.godutch.activity.base.ActivityFrame;
import mobidever.godutch.adapter.AdapterAppGrid;

public class ActivityMain extends ActivityFrame {
    private GridView mGridView;
    private AdapterAppGrid mAdapterAppGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        appendMainBody(R.layout.main_body);
        initVariable();
        initView();
        initListeners();
        bindData();
        //在ActivityFrame里面调用SlideMenu再封装一层，这样外面Activity调用的
        //时候就简化多了。只需要下面一句话就可以了。
        createSlideMenu(R.array.SlideMenuActivityMain);
        //test
    }
    
    private void initVariable() {
        mAdapterAppGrid = new AdapterAppGrid(this);
    }
    
    private void initView() {
        mGridView = (GridView)findViewById(R.id.gvAppGrid);
    }
    
    private void initListeners() {
        
    }
    
    private void bindData() {
        mGridView.setAdapter(mAdapterAppGrid);
    }
    
}
