package com.lhc.android.gz_guide.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lhc.android.gz_guide.R;

/**
 * Created by Administrator on 2017/3/23.
 */
public class OptionsGvAdapter extends BaseAdapter {

    private Context context;
    private int [] optionIcons;
    private int [] optionTexts;

    public OptionsGvAdapter(Context context,int [] icons,int [] texts) {
        this.context = context;
        optionIcons = icons;
        optionTexts = texts;
    }


    @Override
    public int getCount() {
        return optionIcons.length;
    }

    @Override
    public Object getItem(int position) {
        return optionIcons[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.gv_options_item,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.optionText.setText(optionTexts[position]);
        holder.optionView.setImageResource(optionIcons[position]);
        return convertView;
    }

    class ViewHolder{
        ImageView optionView;
        TextView optionText;

        ViewHolder(View itemView){
            optionView = (ImageView) itemView.findViewById(R.id.iv_option_icon);
            optionText = (TextView) itemView.findViewById(R.id.tv_option_text);
        }

    }

}
