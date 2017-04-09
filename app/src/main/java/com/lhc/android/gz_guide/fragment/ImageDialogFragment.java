package com.lhc.android.gz_guide.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.util.ImageCropUtil;

import java.io.File;

public class ImageDialogFragment extends DialogFragment {

    public static final String IMAGE_RESID = "image_resource_id";
    public static final String IMAGE_FILE  = "image_file";
    public static final String IMAGE_URL = "image_url";


    public static final int SOURCE_TYPE_RESOURCE = 1;
    public static final int SOURCE_TYPE_FILE = 2;
    public static final int SOURCE_TYPE_URL = 3;

    private int imgResId = -1;
    private File imageFile;
    private String imgUrl;
    private int sourceType;

    private ImageView imageView;

    public static ImageDialogFragment newInstance(int imgResId) {
        ImageDialogFragment fragment = new ImageDialogFragment();
        Bundle args = new Bundle();
        args.putInt(IMAGE_RESID,imgResId);
        fragment.setArguments(args);
        return fragment;
    }

    public static ImageDialogFragment newInstance(File file){
        ImageDialogFragment fragment = new ImageDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(IMAGE_FILE,file);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static ImageDialogFragment newInstance(String imgUrl){
        ImageDialogFragment fragment = new ImageDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(IMAGE_URL,imgUrl);
        fragment.setArguments(bundle);
        return fragment;
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            imgResId = getArguments().getInt(IMAGE_RESID,-1);
//            if(getArguments().getSerializable(IMAGE_FILE) != null) {
//                imageFile = (File) getArguments().getSerializable(IMAGE_FILE);
//                sourceType = SOURCE_TYPE_FILE;
//            }else{
//                sourceType = SOURCE_TYPE_RESOURCE;
//            }
             imgUrl = getArguments().getString(IMAGE_URL);


        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_big_image_dialog, null);
        imageView = (ImageView) view.findViewById(R.id.iv_show_big_image);
//        if(sourceType == SOURCE_TYPE_RESOURCE) {
//            imageView.setImageResource(imgResId);
//        }else{
//            Bitmap bitmap = ImageCropUtil.decodeFileAsBitmap(imageFile);
//            imageView.setImageBitmap(bitmap);
//        }
        Glide.with(this).load(imgUrl).skipMemoryCache(true).placeholder(R.drawable.loading).into(imageView);
        builder.setView(view);
        Dialog dialog = builder.create();
        return dialog;
    }
}
