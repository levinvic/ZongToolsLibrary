package wangwanglive.levinvic.com.zongtoolslibrary.Utils_Tools;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.loader.ImageLoader;

/**
 * Glide 4.6.1版本
 */

public class GlideImageLoader extends ImageLoader {

    public void displayCircle(Context context, ImageView view, Object path) {
        RequestOptions options = new RequestOptions()
                .circleCrop();
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .skipMemoryCache(true);
        Glide.with(context).load(path).apply(options)
                .transition(new DrawableTransitionOptions().crossFade(500)).into(view);
    }

    public void display(Context context, ImageView view, Object path) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
//                .dontAnimate();
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(false);
        Glide.with(context).load(path).apply(options)
                .transition(new DrawableTransitionOptions().crossFade(500)).into(view);
    }

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(context).load(path).apply(options)
                .transition(new DrawableTransitionOptions().crossFade(500)).into(imageView);
    }
}
