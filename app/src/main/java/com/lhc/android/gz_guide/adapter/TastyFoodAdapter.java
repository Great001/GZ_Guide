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
import com.lhc.android.gz_guide.model.TastyFood;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/25.
 */
public class TastyFoodAdapter extends BaseAdapter {

    private Context context;
    private List<TastyFood> foods  = new ArrayList<>();

    public TastyFoodAdapter(Context context){
        this.context = context;
    }

    public void setData(List<TastyFood> list){
        foods = list;
    }


    @Override
    public int getCount() {
        return foods.size();
    }

    @Override
    public Object getItem(int position) {
        return foods.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_tasty_food_item,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        final TastyFood food = foods.get(position);
        holder.name.setText(food.getName());
        holder.desc.setText(food.getDesc());
        holder.rating.setRating(Float.valueOf(food.getRating()));
        Glide.with(context).load(food.getImgUrl()).placeholder(R.drawable.loading).into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageDialogFragment.newInstance(food.getImgResId()).show(((AppCompatActivity)context).getSupportFragmentManager(),"tasty_food");
            }
        });
        return convertView;
    }

    class ViewHolder{
        ImageView image;
        TextView name;
        TextView desc;
        TextView price;
        RatingBar rating;

        ViewHolder(View itemView){
            image = (ImageView) itemView.findViewById(R.id.iv_tasty_icon);
            name = (TextView) itemView.findViewById(R.id.tv_tasty_name);
            desc = (TextView) itemView.findViewById(R.id.tv_tasty_desc);
            price = (TextView) itemView.findViewById(R.id.tv_tasty_price);
            rating = (RatingBar) itemView.findViewById(R.id.rb_tasty_recommend);
        }

    }

}
