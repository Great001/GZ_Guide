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
import com.lhc.android.gz_guide.model.GzHistory;
import com.lhc.android.gz_guide.util.NavigationUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/23.
 */
public class GzHistroyAdapter extends BaseAdapter {

    private Context context;
    private List<GzHistory> historyList = new ArrayList<>();


    public GzHistroyAdapter(Context context){
        this.context = context;
    }

    public void setData(List<GzHistory> list){
        historyList = list;
    }


    @Override
    public int getCount() {
        return historyList.size();
    }

    @Override
    public Object getItem(int position) {
        return historyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HistoryViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_gz_history_item,null);
            holder = new HistoryViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (HistoryViewHolder) convertView.getTag();
        }

        final GzHistory history = historyList.get(position);
        holder.tvTitle.setText(history.getTitle());
        holder.tvDesc.setText(history.getDesc());
        Glide.with(context).load(history.getImgUrl()).placeholder(R.drawable.loading).into(holder.ivPic);

        holder.ivPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageDialogFragment.newInstance(history.getImgUrl()).show(((AppCompatActivity)context).getSupportFragmentManager(),"GzHistory");
            }
        });
//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                NavigationUtil.navigateToWebViewActivity(context,history.getContentLink());
//            }
//        });
        return convertView;
    }

    class HistoryViewHolder{
        ImageView ivPic;
        TextView tvTitle;
        TextView tvDesc;

        HistoryViewHolder(View itemView){
            ivPic = (ImageView) itemView.findViewById(R.id.iv_history_img);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_history_title);
            tvDesc = (TextView) itemView.findViewById(R.id.tv_history_desc);
        }

    }
}
