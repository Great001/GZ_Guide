package com.lhc.android.gz_guide.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.model.LocalPartner;

import java.util.List;

/**
 * Created by Administrator on 2017/3/26.
 */
public class LocalPartnersAdapter extends BaseAdapter {

    private Context context;
    private List<LocalPartner> localPartnerList;

    public LocalPartnersAdapter(Context context,List<LocalPartner> list){
        this.context = context;
        localPartnerList = list;
    }


    @Override
    public int getCount() {
       return  localPartnerList.size();
    }

    @Override
    public Object getItem(int position) {
        return localPartnerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_local_partner_item,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        LocalPartner partner = localPartnerList.get(position);
        holder.name.setText(partner.getName());
        holder.job.setText("职业："+partner.getJob());
        holder.tel.setText("电话:"+partner.getTel());
        holder.requirement.setText("要求："+partner.getRequirment());
        holder.avatar.setImageResource(partner.getAvatarResId());
        if(partner.getSex() == 1){
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
        TextView requirement;
        TextView tel;

        ViewHolder(View itemView){
            avatar = (ImageView) itemView.findViewById(R.id.iv_local_partner_icon);
            sex = (ImageView)  itemView.findViewById(R.id.iv_local_partner_sex);
            name = (TextView)  itemView.findViewById(R.id.tv_local_partner_name);
            job = (TextView) itemView.findViewById(R.id.tv_local_partner_job);
            requirement = (TextView) itemView.findViewById(R.id.tv_local_partner_requirement);
            tel = (TextView) itemView.findViewById(R.id.tv_local_partner_tel);
        }

    }

}
