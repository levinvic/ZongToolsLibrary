package wangwanglive.levinvic.com.librarytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import wangwanglive.levinvic.com.zongtoolslibrary.haha;

public class MainActivity extends AppCompatActivity {


    private AppCompatImageView img;
    private TextView tbx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (AppCompatImageView) findViewById(R.id.img);
        tbx = (TextView) findViewById(R.id.tbx);
        Picasso.get().load(R.drawable.sticker_17).into(img);
        tbx.setText(new haha().aaa());
    }
}
