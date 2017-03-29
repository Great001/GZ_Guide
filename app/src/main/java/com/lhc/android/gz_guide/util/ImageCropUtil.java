package com.lhc.android.gz_guide.util;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Administrator on 2017/3/29.
 */

public class ImageCropUtil {

    public static final String APP_DIR = Environment.getExternalStorageDirectory().getPath() + "/GZ_GUIDE/";
    public static final String IMAGE_PATH = APP_DIR + "image_cache/";
    public static final String AVATAR_FILE_NAME = "avatar.jpeg";
    public static final String BACKGROUND_FILE_NAME = "background.jpeg";

    public static final int REFRESH_AVATAR = 2;
    public static final int REFRESH_BACKGROUND = 3;
    public static final int SELECT_A_PICTURE_FOR_AVATAR = 6;
    public static final int SELECT_A_PICTURE_FOR_BACKGROUND = 7;

    public static Uri mImageUri;
    public static File mAvatar;
    public static File mBackGround;

    //根据系统版本选择一张图片
    public static void selectImage(Activity context, Uri uri, int requestCode) {
        mImageUri = uri;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            selectImageBeforeKitkat(context, requestCode);
        } else {
            selectImageAfterKitkat(context, requestCode);
        }

    }

    public static void selectImageBeforeKitkat(Activity context, int requestCode) {
        if (context != null) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            context.startActivityForResult(intent, requestCode);
        }
    }

    public static void selectImageAfterKitkat(Activity context, int requestCode) {
        if (context != null) {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            context.startActivityForResult(intent, requestCode);
        }
    }

    //进行裁剪
    public static void cropImageUri(Activity activity, Uri uri, int width, int height, int requestCode) {
        if (activity != null) {
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(uri, "image/jpeg");
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", width);
            intent.putExtra("aspectY", height);
            intent.putExtra("outputX", width);
            intent.putExtra("outputY", height);
            intent.putExtra("scale", true);
            intent.putExtra("scaleUpIfNeeded", true);
            intent.putExtra("return-data", false);
            intent.putExtra("noFaceDetection", true);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            activity.startActivityForResult(intent, requestCode);
        }
    }


    //解析图片URI获取Bitmap
    public static Bitmap decodeUriAsBitmap(Context context, Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static Bitmap decodeFileAsBitmap(File file){
        Bitmap bitmap = null;
        if(file != null) {
            bitmap = BitmapFactory.decodeFile(file.getPath());
        }
        return bitmap;
    }



    public static void openOrCreatePicFile() {
        folderIsExist(new File(APP_DIR));
        folderIsExist(new File(IMAGE_PATH));
        mAvatar = new File(IMAGE_PATH, AVATAR_FILE_NAME);
        mBackGround = new File(IMAGE_PATH, BACKGROUND_FILE_NAME);
    }

    public static File getAvatarFile() {
        if(mAvatar == null) {
            openOrCreatePicFile();
        }
        return mAvatar;
    }

    public static File getBackgroundFile() {
        if(mBackGround == null) {
            openOrCreatePicFile();
        }
        return mBackGround;
    }


    public static boolean folderIsExist(File folder) {
        return (!folder.exists() || folder.isFile()) && folder.mkdirs();
    }

    //根据返回的Intent解析出Uri
    public static  Uri getUri(Context context ,Intent data) {
        String albumPicturePath;
        Uri uri = null;
        if (data != null) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                uri = data.getData();
            } else {
                albumPicturePath = getPath(context.getApplicationContext(), data.getData());
                if (albumPicturePath != null) {
                    uri = Uri.fromFile(new File(albumPicturePath));
                }
            }
        }
        return uri;
    }


    //根据系统版本在4.4以下和以上进行分别处理Uri，转化成文件Path
    public  static String getPath(final Context context, final Uri uri) {
        String path = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT &&
                DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                String[] split = DocumentsContract.getDocumentId(uri).split(":");
                if ("primary".equalsIgnoreCase(split[0])) {
                    path = Environment.getExternalStorageDirectory() + File.separator + split[1];
                }
            } else if (isDownloadsDocument(uri)) {
                Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(DocumentsContract.getDocumentId(uri)));
                path = getDataColumn(context, contentUri, null, null);
            } else if (isMediaDocument(uri)) {
                String[] split = DocumentsContract.getDocumentId(uri).split(":");
                String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                String[] selectionArgs = new String[]{split[1]};
                path = getDataColumn(context, contentUri, "_id=?", selectionArgs);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            path = isGooglePhotosUri(uri) ? uri.getLastPathSegment() :
                    getDataColumn(context, uri, null, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            path = uri.getPath();
        }
        return path;
    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String column = "_data";
        String dataColumn = null;
        try {
            cursor = context.getContentResolver().query(uri, new String[]{column}, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                dataColumn = cursor.getString(cursor.getColumnIndexOrThrow(column));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return dataColumn;
    }


    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }


    public static  boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }


    public static  boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }


    public  static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }


}
