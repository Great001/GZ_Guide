package com.lhc.android.gz_guide.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.activity.MainActivity;
import com.lhc.android.gz_guide.fragment.ImageDialogFragment;
import com.lhc.android.gz_guide.model.RecommendGood;
import com.lhc.android.gz_guide.model.RecommendModel;
import com.lhc.android.gz_guide.view.ShareAppendixTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/24.
 */
public class RecommendGoodAdapter extends BaseAdapter {

    private Context context;
    private List<RecommendGood> goods = new ArrayList<>();
    String imgUrl;

    public RecommendGoodAdapter(Context context) {
        this.context = context;
    }

    public void setGoods(List<RecommendGood> list){
        goods = list;
    }


    @Override
    public int getCount() {
        return goods.size();
    }

    @Override
    public Object getItem(int position) {
        return goods.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_recommend_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final RecommendGood good = goods.get(position);
        holder.title.setText(good.getTitle());
        holder.type.setText(good.getType());
        holder.desc.setText(good.getDesc());
        imgUrl = good.getImgUrl();
        Glide.with(context).load(good.getImgUrl()).thumbnail(0.2f).placeholder(R.drawable.loading).centerCrop().into(holder.pic);
        holder.ratingbar.setRating(Float.valueOf(good.getRating()));
        holder.thumbsUp.setImageResource(good.isPhiase() ? R.drawable.have_give_shumbs_up : R.drawable.havent_give_thumbs_up);

        holder.pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageDialogFragment.newInstance(good.getImgUrl()).show(((MainActivity) context).getSupportFragmentManager(), "dialog");
            }
        });

        holder.thumbsUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(good.isPhiase()){
                    ((ImageView)v).setImageResource(R.drawable.havent_give_thumbs_up);
                    good.setPhiase(false);
                }else{
                    ((ImageView)v).setImageResource(R.drawable.have_give_shumbs_up);
                    good.setPhiase(true);
//                    Float rating = Float.valueOf(good.getRating()) + 1;
                }

            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView title;
        TextView type;
        TextView desc;
        ImageView pic;
        RatingBar ratingbar;
        ImageView thumbsUp;
        ImageView comment;

        ViewHolder(View itemView) {
            title = (TextView) itemView.findViewById(R.id.tv_recommend_goods_title);
            type = (TextView) itemView.findViewById(R.id.tv_recommend_goods_type);
            desc = (TextView) itemView.findViewById(R.id.tv_recommend_goods_desc);
            pic = (ImageView) itemView.findViewById(R.id.iv_recommend_goods_pic);
            ratingbar = (RatingBar) itemView.findViewById(R.id.rb_recommend_goods);
            thumbsUp = (ImageView) itemView.findViewById(R.id.iv_give_thumbs_up);
            comment = (ImageView) itemView.findViewById(R.id.iv_recomment_comment);
        }

    }


}
