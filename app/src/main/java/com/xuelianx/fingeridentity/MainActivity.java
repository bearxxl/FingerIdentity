package com.xuelianx.fingeridentity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.xuelianx.fingerlib.FingerFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FingerFragment fingerFragment = new FingerFragment();
                fingerFragment.show(getFragmentManager(),"fingerFragment");
                fingerFragment.setmFragmentCallBack(new FingerFragment.Callback() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(MainActivity.this, "成功", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError() {
                        Toast.makeText(MainActivity.this, "弹密码框", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
