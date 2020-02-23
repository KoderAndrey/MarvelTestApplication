package com.example.marveltestapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.marveltestapplication.R;
import com.example.marveltestapplication.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PersonageActivity.class));
            }
        });
    }
}
