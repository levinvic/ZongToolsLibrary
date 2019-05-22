package wangwanglive.levinvic.com.librarytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.widget.TextView;

import wangwanglive.levinvic.com.zongtoolslibrary.haha;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatImageView img = findViewById(R.id.img);
        TextView tbx = findViewById(R.id.tbx);

        tbx.setText(new haha().aaa());

    }
}
