package com.lhc.android.gz_guide.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.fragment.ImageDialogFragment;
import com.lhc.android.gz_guide.model.Spot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/26.
 */
public class SpotsAdapter extends BaseAdapter {

    private Context context;
    private List<Spot> spots = new ArrayList<>();

    public SpotsAdapter(Context context){
        this.context = context;
    }

    public void setData(List<Spot> list){
        spots = list;
    }

    @Override
    public int getCount() {
        return spots.size();
    }

    @Override
    public Object getItem(int position) {
        return spots.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_spots_item,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        final Spot spot = spots.get(position);
        holder.name.setText(spot.getName());
        holder.desc.setText(spot.getDesc());
        holder.ticket.setText(spot.getTicketPrice() + "元/人");
        holder.address.setText(spot.getAddress());
        holder.rating.setRating(Float.valueOf(spot.getRating()));

        Glide.with(context).load(spot.getImgUrl()).placeholder(R.drawable.loading).into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageDialogFragment.newInstance(spot.getImgResId()).show(((AppCompatActivity)context).getSupportFragmentManager(),"SPOT");
            }
        });
        return convertView;
    }

    class ViewHolder{
        ImageView image;
        TextView name;
        TextView desc;
        TextView ticket;
        TextView address;
        RatingBar rating;

        ViewHolder(View itemView){
            image = (ImageView) itemView.findViewById(R.id.iv_spot_icon);
            name = (TextView)  itemView.findViewById(R.id.tv_spot_name);
            desc = (TextView) itemView.findViewById(R.id.tv_spot_desc);
            address = (TextView) itemView.findViewById(R.id.tv_spot_address);
            ticket = (TextView) itemView.findViewById(R.id.tv_spot_ticket_price);
            rating = (RatingBar) itemView.findViewById(R.id.rb_spot_rating);
        }
    }




}
