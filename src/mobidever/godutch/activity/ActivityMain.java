
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
