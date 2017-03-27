package com.lhc.android.gz_guide.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.PoiAddrInfo;
import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.util.NavigationUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/27.
 */
public class AddrSearchResultAdapter extends BaseAdapter {

    private Context context;
    private List<PoiAddrInfo> addrInfos;
    private List<PoiInfo> poiInfos;

    public AddrSearchResultAdapter(Context context) {
        this.context = context;
    }

    public void setAddrInfos(List<PoiAddrInfo> list){
        addrInfos = list;
    }

    public void setPoiInfos(List<PoiInfo> list){
        poiInfos = list;
    }

    @Override
    public int getCount() {
        if(addrInfos != null){
            return addrInfos.size();
        }
        return poiInfos.size();
    }

    @Override
    public Object getItem(int position) {
        if(addrInfos!=null){
            return addrInfos.get(position);
        }
        return poiInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_addr_search_result_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final LatLng location;
        final String address;
        if(addrInfos != null) {
            PoiAddrInfo addrInfo = addrInfos.get(position);
            holder.addrName.setText(addrInfo.name);
            holder.addrDetail.setText(addrInfo.address);
            location = addrInfo.location;
            address = addrInfo.name;
        }else{
            PoiInfo poiInfo = poiInfos.get(position);
            holder.addrDetail.setText(poiInfo.address);
            holder.addrName.setText(poiInfo.name);
            location = poiInfo.location;
            address = poiInfo.address;
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationUtil.navigateToBmapActivity(context,location,address);
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView addrName;
        TextView addrDetail;

        ViewHolder(View itemView) {
            addrName = (TextView) itemView.findViewById(R.id.tv_addr_name);
            addrDetail = (TextView) itemView.findViewById(R.id.tv_addr_detail);
        }

    }

}
