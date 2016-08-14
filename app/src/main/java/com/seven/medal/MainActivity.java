package com.seven.medal;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Runnable{
    private ListView lv = null;
    private DataPares dp = null;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            /*System.out.println((int)msg.obj);
            tv_gold.setText((int)msg.obj);*/
            update();

        }
    };

    private void update() {
        lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(new ListAdapter(dp,this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(this).start();

    }

    @Override
    public void run() {
        dp = new DataPares();
        handler.sendEmptyMessage(0);
    }


}
