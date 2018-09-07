package com.msht.mshtlpgmaster.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;


import java.io.File;
import java.util.concurrent.ExecutionException;


/**
 * 针对glide3.6
 * 网络图片加载帮助类
 *
 * @author qyf
 * @date 2016-05-19
 */
public class GildeLoadImageUtils {/*

 *//**
 * 加载网络图片
 *
 * @param context
 * @param imageView
 * @param imgUrl
 *//*
    public static void loadRemoteImg(Context context, ImageView imageView, String imgUrl) {
        Glide.with(context)
                .load(imgUrl)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.drawable.nc_icon_null)  //设置占位图
                .error(R.drawable.nc_icon_null)      //加载错误图
                .into(imageView);
    }

    *//**
 * 加载网络图片（没有淡入淡出动画效果）
 *
 * @param context
 * @param imageView
 * @param imgUrl
 *//*
    public static void loadRemoteImgDontAnimate(Context context, ImageView imageView, String imgUrl) {
        Glide.with(context)
                .load(imgUrl)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .dontAnimate()
                .crossFade(0)
                .placeholder(R.drawable.nc_icon_null)  //设置占位图
                .error(R.drawable.nc_icon_null)      //加载错误图
                .into(imageView);
    }

    *//**
 * 加载本地图片
 *
 * @param context
 * @param imageView
 * @param path
 *//*
    public static void loadLocalImg(Context context, ImageView imageView, String path) {
        Glide.with(context)
                .load(new File(path))
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .priority(Priority.HIGH)
                .dontAnimate()
                .crossFade(0)
                .placeholder(R.drawable.nc_icon_null)  //设置占位图
                .error(R.drawable.nc_icon_null)      //加载错误图
                .into(imageView);
    }

    *//**
 * 加载网络图片（有尺寸）
 *
 * @param context
 * @param imageView
 * @param url
 * @param width     px
 * @param height    px
 *//*
    public static void loadRemoteImg(Context context, ImageView imageView, String url, int width, int height) {
        String[] s = url.split("\\.");
        String imgUrl = url + "_" + width + "x" + height + "." + s[s.length - 1];
        Glide.with(context)
                .load(imgUrl)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.drawable.nc_icon_null)  //设置占位图
                .error(R.drawable.nc_icon_null)      //加载错误图
                .into(imageView);
    }

    *//**
 * 加载网络图片（有尺寸）（没有淡入淡出动画效果）
 *
 * @param context
 * @param imageView
 * @param url
 * @param width     px
 * @param height    px
 *//*
    public static void loadRemoteImgDontAnimate(Context context, ImageView imageView, String url, int width, int height) {
        String[] s = url.split("\\.");
        String imgUrl = url + "_" + width + "x" + height + "." + s[s.length - 1];
        Glide.with(context)
                .load(imgUrl)
                .fitCenter()
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.drawable.nc_icon_null)  //设置占位图
                .error(R.drawable.nc_icon_null)      //加载错误图
                .into(imageView);
    }

    *//**
 * 加载Gif图片
 *
 * @param context
 * @param imageView
 * @param data
 *//*
    public static void loadGifImg(Context context, ImageView imageView, int data) {
        Glide.with(context)
                .load(data)
                .asGif()
                .fitCenter()
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.drawable.nc_icon_null)  //设置占位图
                .error(R.drawable.nc_icon_null)      //加载错误图
                .into(imageView);
    }

    *//**
 * 自适应宽度加载图片。保持图片的长宽比例不变，通过修改imageView的高度来完全显示图片。
 *//*
    public static void loadIntoUseFitWidth(Context context, final ImageView imageView, final String imageUrl) {
        Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (imageView == null) {
                            return false;
                        }
                        if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        }
                        ViewGroup.LayoutParams params = imageView.getLayoutParams();
                        int vw = imageView.getWidth() - imageView.getPaddingLeft() - imageView.getPaddingRight();
                        float scale = (float) vw / (float) resource.getIntrinsicWidth();
                        int vh = Math.round(resource.getIntrinsicHeight() * scale);
                        params.height = vh + imageView.getPaddingTop() + imageView.getPaddingBottom();
                        imageView.setLayoutParams(params);
                        return false;
                    }
                })
                .placeholder(R.drawable.nc_icon_null)  //设置占位图
                .error(R.drawable.nc_icon_null)      //加载错误图
                .into(imageView);
    }


    *//**
 * 加载本地图片
 *
 * @param context
 * @param imageView
 * @param resId
 *//*
    public static void loadLocalGreyImg(Context context, ImageView imageView, int resId) {
        Drawable drawable = context.getResources().getDrawable(resId);
        Bitmap bitmap = drawableToBitmap(drawable);
        imageView.setImageBitmap(grey(context, bitmap));
    }

    *//**
 * drawable 转换成bitmap
 *
 * @param drawable
 * @return
 *//*
    public static Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();// 取drawable的长宽
        int height = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;// 取drawable的颜色格式
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);// 建立对应bitmap
        Canvas canvas = new Canvas(bitmap);// 建立对应bitmap的画布
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);// 把drawable内容画到画布中
        return bitmap;
    }

    private static Bitmap grey(Context context, Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Bitmap faceIconGreyBitmap = Bitmap
                .createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Bitmap alphaBitmap = bitmap.extractAlpha();
        Canvas canvas = new Canvas(faceIconGreyBitmap);
        Paint paint = new Paint();
//        ColorMatrix colorMatrix = new ColorMatrix();
//        colorMatrix.setSaturation(0);
//        ColorMatrixColorFilter colorMatrixFilter = new ColorMatrixColorFilter(
//                colorMatrix);
//        paint.setColorFilter(colorMatrixFilter);
        paint.setColor(context.getResources().getColor(R.color.buystep_sku_text));
        canvas.drawBitmap(alphaBitmap, 0, 0, paint);
        return faceIconGreyBitmap;
    }

    *//**
 * 清除图片的缓存
 *
 * @param context
 *//*
    public static void clearCache(Context context) {
        Glide.get(context).clearMemory();
        //Glide.get(context).clearDiskCache(); //需要在子线程执行
    }

    *//**
 * 圆角图片
 *
 * @param context
 * @param imageView
 * @param url
 *//*
    public static void loadRemoteCircleImg(Context context, ImageView imageView, String url) {
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }

    *//**
 * 加载旋转图片
 *
 * @param context
 * @param imageView
 * @param degree
 * @param resourceId
 *//*
    public static void loadImageRotated(Context context, ImageView imageView, float degree, int resourceId) {
        Glide.with(context)
                .load(resourceId)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .transform(new RotateTransformation(context, degree))
                .into(imageView);
    }

    *//**
 * 通过文件选择器返回的Uri获取文件Path
 * 由于4.4及以上版本返回的Uri会有多种格式需要提前判断处理再调用getImagePath
 *
 * @param context
 * @param uri
 * @return
 *//*
    public static String getPath(Context context, Uri uri) {
        String imagePath = null;
        if (DocumentsContract.isDocumentUri(context, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(docId));
                imagePath = getImagePath(context, contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagePath(context, uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return imagePath;
    }

    private static String getImagePath(Context context, Uri uri, String selection) {
        String path = null;
        Cursor cursor = context.getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }

            cursor.close();
        }
        return path;
    }


    *//**
 * 加载网络图片（没有淡入淡出动画效果）
 *
 * @param context
 * @param imgUrl
 *//*
    public static String getLocalCachePath(Context context, String imgUrl) {

        FutureTarget<File> future = Glide.with(context)
                .load(imgUrl)
                .downloadOnly(500, 500);
        try {
            File cacheFile = future.get();
            String path = cacheFile.getAbsolutePath();
            return path;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    */

    /**
     * Bitmap缩放大小的方法
     *//*
    public static Bitmap scale(Bitmap bitmap, float w, float h) {
        Matrix matrix = new Matrix();
        matrix.postScale(w, h);  //长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizeBmp;
    }
*/
//针对glide4.X
    public static void loadRemoteImg(Context context, ImageView imageView, String imgUrl, Drawable placeholderDrawble, Drawable errorDrawable) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(placeholderDrawble)
                .error(errorDrawable)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .priority(Priority.HIGH);
        Glide.with(context)
                .load(imgUrl)
                .apply(options)
                .into(imageView);
    }

    /**
     * 加载网络图片（没有淡入淡出动画效果）
     *
     * @param context
     * @param imageView
     * @param imgUrl
     */
    public static void loadRemoteImgDontAnimate(Context context, ImageView imageView, String imgUrl,Drawable placeholderDrawble, Drawable errorDrawable) {

        RequestOptions options = new RequestOptions()
                .fitCenter()
                .dontAnimate()
                .placeholder(placeholderDrawble)
                .error(errorDrawable)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .priority(Priority.HIGH);

        Glide.with(context)
                .load(imgUrl)
                .apply(options)
                .into(imageView);
    }

    /**
            * 加载本地图片
 *
         * @param context
 * @param imageView
 * @param path
 */
    public static void loadLocalImg(Context context, ImageView imageView, String path,Drawable placeholderDrawble, Drawable errorDrawable) {
        RequestOptions options = new RequestOptions()
                .fitCenter()
                .dontAnimate()
                .placeholder(placeholderDrawble)
                .error(errorDrawable)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .priority(Priority.HIGH);

        Glide.with(context)
                .load(new File(path))
                .apply(options)
                .into(imageView);
    }
    /**
     * 加载Gif图片
     *
     * @param context
     * @param imageView
     * @param data
     */
    public static void loadGifImg(Context context, ImageView imageView, int data,Drawable placeholderDrawble, Drawable errorDrawable) {
        RequestOptions options = new RequestOptions()
                .fitCenter()
                .dontAnimate()
                .placeholder(placeholderDrawble)
                .error(errorDrawable)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .priority(Priority.HIGH);

        Glide.with(context)
                .asGif()
                .load(data)
                .apply(options)
                .into(imageView);
    }
    /**
            * 自适应imageview宽度加载图片。保持图片的长宽比例不变，通过修改imageView的高度来完全显示图片。
            */
    public static void loadIntoUseFitWidth(Context context, final ImageView imageView, final String imageUrl,Drawable placeholderDrawble, Drawable errorDrawable) {
        RequestOptions options = new RequestOptions()
                .fitCenter()
                .dontAnimate()
                .placeholder(placeholderDrawble)
                .error(errorDrawable)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .priority(Priority.HIGH);

        Glide.with(context)
                .load(imageUrl)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        if (imageView == null) {
                            return false;
                        }
                        if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        }
                        ViewGroup.LayoutParams params = imageView.getLayoutParams();
                        int vw = imageView.getWidth() - imageView.getPaddingLeft() - imageView.getPaddingRight();
                        float scale = (float) vw / (float) resource.getIntrinsicWidth();
                        int vh = Math.round(resource.getIntrinsicHeight() * scale);
                        params.height = vh + imageView.getPaddingTop() + imageView.getPaddingBottom();
                        imageView.setLayoutParams(params);
                        return false;
                    }
                })
                .apply(options)
                .into(imageView);

    }

    /**
     * 以宽为基准，更改图片长宽比后,imageview Wrapcontent图片
     */
    public static void loadIntoByAssignScale(Context context, final ImageView imageView, final String imageUrl,Drawable placeholderDrawble, Drawable errorDrawable,int withHeighScale) {
        RequestOptions options = new RequestOptions()
                .fitCenter()
                .dontAnimate()
                .placeholder(placeholderDrawble)
                .error(errorDrawable)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .priority(Priority.HIGH);

        Glide.with(context)
                .load(imageUrl)
                .apply(options)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        if (imageView == null) {
                            return ;
                        }
                        if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        }
                        Bitmap bitmap =DrawbleUtil.drawableToBitmap(resource);
                        int width = bitmap.getWidth();
                        int heightOrigin= bitmap.getHeight();
                        Matrix matrix = new Matrix();
                        int height=width/withHeighScale;
                        int h = height/heightOrigin;
                        matrix.postScale(1, h);  //宽高变化系数
                        Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0,width, height, matrix, true);
                        imageView.setImageBitmap(resizeBmp);
                    }
                });

    }


    /**
     * 圆角图片
     *
     * @param context
     * @param imageView
     * @param url
     */
    public static void loadRemoteCircleImg(Context context, ImageView imageView, String url,Drawable placeholderDrawble, Drawable errorDrawable) {
        RequestOptions options = new RequestOptions()
                .fitCenter()
                .dontAnimate()
                .placeholder(placeholderDrawble)
                .error(errorDrawable)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .transform(new CircleCrop())
                .priority(Priority.HIGH);
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }
    /**
     * 加载网络图片（没有淡入淡出动画效果）
     *
     * @param context
     * @param imgUrl
     */
    public static String getLocalCachePath(Context context, String imgUrl) {

        FutureTarget<File> future = Glide.with(context)
                .load(imgUrl)
                .downloadOnly(500, 500);
        try {
            File cacheFile = future.get();
            String path = cacheFile.getAbsolutePath();
            return path;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

}
