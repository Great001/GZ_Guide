package com.lhc.android.gz_guide.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.fragment.ImageDialogFragment;
import com.lhc.android.gz_guide.model.Strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/26.
 */
public class StrageriesAdapter extends BaseAdapter {

    private Context context;
    private List<Strategy> strageryList = new ArrayList<>();

    public StrageriesAdapter(Context context){
        this.context = context;
    }

    public void setData(List<Strategy> list){
        strageryList = list;
    }


    @Override
    public int getCount() {
        return strageryList.size();
    }

    @Override
    public Object getItem(int position) {
        return strageryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_stragery_item,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        final Strategy stragery = strageryList.get(position);
        holder.title.setText(stragery.getTitle());
        holder.desc.setText(stragery.getDesc());
        holder.readCount.setText(stragery.getReadCount()+"");
        holder.commentCount.setText(stragery.getCommentCount()+"");
        Glide.with(context).load(stragery.getImgUrl()).placeholder(R.drawable.loading).into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageDialogFragment.newInstance(stragery.getImgResId()).show(((AppCompatActivity)context).getSupportFragmentManager(),"stragery");
            }
        });
        return convertView;
    }

    class ViewHolder {
        ImageView image;
        TextView title;
        TextView desc;
        TextView readCount;
        TextView commentCount;

        ViewHolder(View itemView){
            image = (ImageView) itemView.findViewById(R.id.iv_stragery_icon);
            title = (TextView) itemView.findViewById(R.id.tv_stragery_title);
            desc = (TextView) itemView.findViewById(R.id.tv_stragery_desc);
            readCount = (TextView) itemView.findViewById(R.id.tv_stragery_readCount);
            commentCount = (TextView) itemView.findViewById(R.id.tv_stragery_commentCount);
        }

    }

}
