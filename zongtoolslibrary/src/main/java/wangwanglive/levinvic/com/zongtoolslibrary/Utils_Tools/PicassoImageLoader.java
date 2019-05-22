package wangwanglive.levinvic.com.zongtoolslibrary.Utils_Tools;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;

public class PicassoImageLoader extends ImageLoader {

    public void display(String path,ImageView imageView){
        Picasso.get().load(path).into(imageView);
    }

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Picasso.get().load(String.valueOf(path)).into(imageView);
    }
}
