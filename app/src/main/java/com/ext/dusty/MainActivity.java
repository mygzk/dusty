package com.ext.dusty;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ext.dusty.activity.RevealActivity;
import com.ext.dusty.activity.VibratorActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.rv_entries)
    RecyclerView mEntryList;
    static List<Entry> mEntries = new ArrayList<>();
    static {
        mEntries.add(new Entry("RevealActivity", RevealActivity.class));
        mEntries.add(new Entry("VibratorActivity", VibratorActivity.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        final Adaptor adaptor=new Adaptor();
        mEntryList.setAdapter(adaptor);
        mEntryList.addItemDecoration(new ItemDivider(this, R.drawable.divider));
        adaptor.setOnItemClickLitener(new Adaptor.OnItemClickLitener() {
            public void onClick(View parent, int position) {
                Intent intent = new Intent(MainActivity.this,mEntries.get(position).clazz);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, position + " long click",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    static class Adaptor extends  RecyclerView.Adapter<Adaptor.holder>{
        @Override
        public holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.entry_item,parent,false);
            return new holder(v);
        }

        @Override
        public void onBindViewHolder(final holder holder, int position) {
            holder.tv.setText(mEntries.get(position).description);
            holder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if (mOnItemClickLitener != null) {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickLitener.onClick(v, pos);
                    }
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }

        @Override
        public int getItemCount() {
            return mEntries.size();
        }

        public void removeData(int position) {
            mEntries.remove(position);
            notifyItemRemoved(position);
        }
        private OnItemClickLitener mOnItemClickLitener;
        public interface OnItemClickLitener {
            public void onClick(View parent, int position);
            void onItemLongClick(View view , int position);
        }
        public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
        {
            this.mOnItemClickLitener = mOnItemClickLitener;
        }

        class holder extends RecyclerView.ViewHolder{
           TextView tv;
           public View itemView;
           public holder(View itemView) {
               super(itemView);
               this.itemView = itemView;
               tv = (TextView) itemView.findViewById(R.id.tv_entry);
           }
       }
    }
    static class Entry {
        public String description;
        public Class<?> clazz;
        public Entry(String description, Class<?> clazz) {
            this.description = description;
            this.clazz = clazz;
        }
    }
    public class ItemDivider extends RecyclerView.ItemDecoration {

        private Drawable mDrawable;

        public ItemDivider(Context context, int resId) {
            //在这里我们传入作为Divider的Drawable对象
            mDrawable = context.getResources().getDrawable(resId);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent) {
            final int left = parent.getPaddingLeft();
            final int right = parent.getWidth() - parent.getPaddingRight();

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                //以下计算主要用来确定绘制的位置
                final int top = child.getBottom() + params.bottomMargin;
                final int bottom = top + mDrawable.getIntrinsicHeight();
                mDrawable.setBounds(left, top, right, bottom);
                mDrawable.draw(c);
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, int position, RecyclerView parent) {
            outRect.set(0, 0, 0, mDrawable.getIntrinsicWidth());
        }
    }
}
