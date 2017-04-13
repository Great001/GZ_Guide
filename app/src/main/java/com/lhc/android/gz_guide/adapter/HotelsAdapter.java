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
import com.lhc.android.gz_guide.model.Hotel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/25.
 */
public class HotelsAdapter extends BaseAdapter {

    private Context context;
    private List<Hotel> hotels = new ArrayList<>();

    public HotelsAdapter(Context context) {
        this.context = context;
    }


    public void setData(List<Hotel> list){
        this.hotels = list;
    }



    @Override
    public int getCount() {
        return hotels.size();
    }

    @Override
    public Object getItem(int position) {
        return hotels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_hotels_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Hotel hotel = hotels.get(position);
        holder.name.setText(hotel.getName());
        holder.type.setText(hotel.getType());
        holder.price.setText(hotel.getLeastPrice());
        holder.state.setText(hotel.isAvailable() ? "有房" : "满房");
        holder.address.setText(hotel.getAddress());
        holder.rating.setRating(Float.valueOf(hotel.getRating()));

        Glide.with(context).load(hotel.getImgUrl()).placeholder(R.drawable.loading).into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageDialogFragment.newInstance(hotel.getImgResId()).show(((AppCompatActivity)context).getSupportFragmentManager(),"hotel");
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView name;
        TextView type;
        TextView price;
        TextView state;
        TextView address;
        RatingBar rating;
        ImageView image;

        ViewHolder(View itemView) {
            name = (TextView) itemView.findViewById(R.id.tv_hotel_name);
            type = (TextView) itemView.findViewById(R.id.tv_hotel_type);
            price = (TextView) itemView.findViewById(R.id.tv_hotel_price);
            state = (TextView) itemView.findViewById(R.id.tv_hotel_isFull);
            address = (TextView) itemView.findViewById(R.id.tv_hotel_address);
            rating = (RatingBar) itemView.findViewById(R.id.tb_hotel_rating);
            image = (ImageView) itemView.findViewById(R.id.iv_hotel_icon);
        }

    }

}
