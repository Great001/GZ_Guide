package com.lhc.android.gz_guide.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.lhc.android.gz_guide.R;

public class ImageDialogFragment extends DialogFragment {

    public static final String IMAGE_RESID = "image_resource_id";

    private int imgResId;

    private ImageView imageView;

    public static ImageDialogFragment newInstance(int imgResId) {
        ImageDialogFragment fragment = new ImageDialogFragment();
        Bundle args = new Bundle();
        args.putInt(IMAGE_RESID,imgResId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imgResId = getArguments().getInt(IMAGE_RESID);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_big_image_dialog, null);
        imageView = (ImageView) view.findViewById(R.id.iv_show_big_image);
        imageView.setImageResource(imgResId);
        builder.setView(view);
        Dialog dialog = builder.create();
        return dialog;
    }
}
