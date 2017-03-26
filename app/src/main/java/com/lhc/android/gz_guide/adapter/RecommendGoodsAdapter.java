package com.lhc.android.gz_guide.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.activity.MainActivity;
import com.lhc.android.gz_guide.fragment.ImageDialogFragment;
import com.lhc.android.gz_guide.model.RecommendGood;

import java.util.List;

/**
 * Created by Administrator on 2017/3/24.
 */
public class RecommendGoodsAdapter extends BaseAdapter {

    private Context context;
    private List<RecommendGood> goods;


    public RecommendGoodsAdapter(Context context, List<RecommendGood> lists) {
        this.context = context;
        goods = lists;

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
        holder.desc.setText(good.getDesc());
        holder.pic.setImageResource(good.getImgRes());
        holder.ratingbar.setRating(good.getRating());
        holder.thumbsUp.setImageResource(good.isPhiase() ? R.drawable.have_give_shumbs_up : R.drawable.havent_give_thumbs_up);

        holder.pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageDialogFragment.newInstance(good.getImgRes()).show(((MainActivity) context).getSupportFragmentManager(), "dialog");
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
                }

            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView title;
        TextView desc;
        ImageView pic;
        RatingBar ratingbar;
        ImageView thumbsUp;
        ImageView comment;

        ViewHolder(View itemView) {
            title = (TextView) itemView.findViewById(R.id.tv_recommend_goods_title);
            desc = (TextView) itemView.findViewById(R.id.tv_recommend_goods_desc);
            pic = (ImageView) itemView.findViewById(R.id.iv_recommend_goods_pic);
            ratingbar = (RatingBar) itemView.findViewById(R.id.rb_recommend_goods);
            thumbsUp = (ImageView) itemView.findViewById(R.id.iv_give_thumbs_up);
            comment = (ImageView) itemView.findViewById(R.id.iv_recomment_comment);
        }

    }


}
