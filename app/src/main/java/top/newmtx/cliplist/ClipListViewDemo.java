package top.newmtx.cliplist;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import top.newmtx.library.adapter.LVBaseAdapter;
import top.newmtx.library.clip.ClipHorizontalScrollView;
import top.newmtx.library.clip.ClipListView;

public class ClipListViewDemo extends Activity {

    private Context mContext=ClipListViewDemo.this;
    ClipListView rv_list;
    private Adapter mRecycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.ui_clip_list_demo);
        width=getWindowManager().getDefaultDisplay().getWidth();
        rv_list= (ClipListView) findViewById(R.id.rv_list);

        //设置是否在切换item首先检查拦截展开事件
        rv_list.setInterceptBeforeTouch(false);

        ArrayList<String> arrayList=new ArrayList<>();
        for(int i=0;i<100;i++){
            arrayList.add("ITEM:"+i);
        }
        mRecycleAdapter=new Adapter(mContext,arrayList);
        rv_list.setAdapter(mRecycleAdapter);
    }

    private int width=80;

    class Adapter extends LVBaseAdapter<String,Adapter.ItemViewHolder> {


        public Adapter(Context context, List<String> initDatas) {
            super(context, initDatas);
        }

        @Override
        public ItemViewHolder getItemHolder(LayoutInflater inflater, ViewGroup parent, int position) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.list_item_h, parent,false);
            return  new ItemViewHolder(inflate);
        }

        @Override
        public void bindData(ItemViewHolder holder, int position, String itemData) {
            holder.refreshData(position);
            holder.mHorizontalScrollView.scrollTo(0,0);

        }

        class ViewHolder extends RecyclerView.ViewHolder{
            public ViewHolder(View itemView) {
                super(itemView);
            }
        }

        class ItemViewHolder extends LVBaseAdapter.LVBaseViewHolder implements View.OnClickListener{
            private ClipHorizontalScrollView mHorizontalScrollView;
            private ImageView mImageView;
            private TextView tv_delete,tv_add;

            private int position=-1;

            public ItemViewHolder(View itemView) {
                super(itemView);
                mHorizontalScrollView= (ClipHorizontalScrollView) itemView.findViewById(R.id.hsv_item);
                mImageView= (ImageView) itemView.findViewById(R.id.iv_img);
                tv_delete= (TextView) itemView.findViewById(R.id.tv_delete);
                tv_add= (TextView) itemView.findViewById(R.id.tv_add);
                ClipHorizontalScrollView.onRelayoutGrandson(mImageView,width);
                tv_delete.setOnClickListener(this);
                tv_add.setOnClickListener(this);
            }

            public void refreshData(int position){
                this.position=position;
            }

            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.tv_add:
                        showToast(position+":添加");
                        break;
                    case R.id.tv_delete:
                        showToast(position+":删除");
                        break;
                }
            }
        }
    }

    private void showToast(String msg){
        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
    }
}
