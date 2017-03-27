package com.lhc.android.gz_guide.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.fragment.ImageDialogFragment;
import com.lhc.android.gz_guide.model.Stragery;

import java.util.List;

/**
 * Created by Administrator on 2017/3/26.
 */
public class StrageriesAdapter extends BaseAdapter {

    private Context context;
    private List<Stragery> strageryList;

    public StrageriesAdapter(Context context,List<Stragery> list){
        this.context = context;
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
        final Stragery stragery = strageryList.get(position);
        holder.title.setText(stragery.getTitle());
        holder.desc.setText(stragery.getDesc());
        holder.readCount.setText(stragery.getReadCount()+"");
        holder.commentCount.setText(stragery.getCommentCount()+"");
        holder.image.setImageResource(stragery.getImgResId());
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
