package com.lhc.android.gz_guide.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.model.LocalGuide;

import java.util.List;

/**
 * Created by Administrator on 2017/3/26.
 */
public class LocalGuiderAdapter extends BaseAdapter {

    private Context context;
    private List<LocalGuide> localGuideList;

    public LocalGuiderAdapter(Context context,List<LocalGuide> list){
        this.context = context;
        localGuideList = list;
    }

    @Override
    public int getCount() {
        return localGuideList.size();
    }

    @Override
    public Object getItem(int position) {
        return localGuideList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_local_guide_item,null);
            holder = new ViewHolder(convertView);;
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        LocalGuide guide = localGuideList.get(position);
        holder.name.setText(guide.getName());
        holder.job.setText("职业："+ guide.getJob());
        holder.range.setText("等级：" + guide.getRange());
        holder.tel.setText("tel:"+guide.getTel());
        holder.briefInfo.setText("简介:"+guide.getBriefInfo());

        holder.avatar.setImageResource(guide.getThumpResId());
        if(guide.getSex() == 1){
            holder.sex.setImageResource(R.drawable.icn_sex_male);
        }else{
            holder.sex.setImageResource(R.drawable.icn_sex_female);
        }
        return convertView;
    }

    class ViewHolder{
        ImageView avatar;
        ImageView sex;
        TextView name;
        TextView job;
        TextView range;
        TextView briefInfo;
        TextView tel;

        ViewHolder(View itemView){
            avatar = (ImageView) itemView.findViewById(R.id.iv_local_guide_avatar);
            sex = (ImageView) itemView.findViewById(R.id.iv_local_guide_sex);
            name = (TextView) itemView.findViewById(R.id.tv_local_guide_name);
            job = (TextView) itemView.findViewById(R.id.tv_local_guide_job);
            range = (TextView) itemView.findViewById(R.id.tv_local_guide_range);
            briefInfo = (TextView) itemView.findViewById(R.id.tv_local_guide_briefInfo);
            tel = (TextView) itemView.findViewById(R.id.tv_local_guide_tel);
        }

    }

}
