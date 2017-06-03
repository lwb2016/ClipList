package top.newmtx.cliplist;

import android.app.LauncherActivity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class DemoMain extends LauncherActivity {
    private List<String> mList;
    private List<Intent> mClasses ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mList = fillArray();
        mClasses = fillClassArray();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, mList);
        this.setListAdapter(adapter);
    }

    private List<Intent> fillClassArray() {
        List<Intent> list=new ArrayList<>();
        list.add(new Intent(this,ClipListViewDemo.class));
        list.add(new Intent(this,ClipGridViewDemo.class));
        list.add(new Intent(this,ClipRecycleViewDemo.class));
        return list;
    }

    private List<String> fillArray() {
        List<String> list=new ArrayList<>();
        list.add("ListView侧滑测试");
        list.add("GridView侧滑测试");
        list.add("RecycleView侧滑测试");
        return list;
    }

    @Override
    protected Intent intentForPosition(int position) {
        return mClasses.get(position);
    }
}
