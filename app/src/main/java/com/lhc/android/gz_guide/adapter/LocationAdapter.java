package com.lhc.android.gz_guide.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baidu.location.Poi;
import com.lhc.android.gz_guide.R;

import java.util.List;

/**
 * Created by Administrator on 2017/3/27.
 */
public class LocationAdapter extends BaseAdapter {

    private Context context;
    private List<Poi> poiList;

    public LocationAdapter(Context context,List<Poi> list){
        this.context = context;
        poiList = list;
    }


    @Override
    public int getCount() {
        return poiList.size();
    }

    @Override
    public Object getItem(int position) {
        return poiList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_location_item,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Poi poi = poiList.get(position);
        holder.name.setText(poi.getName());
        return convertView;
    }

    class ViewHolder{
        TextView name;

        ViewHolder(View itemView){
            name = (TextView) itemView.findViewById(R.id.tv_location);
        }

    }

}
