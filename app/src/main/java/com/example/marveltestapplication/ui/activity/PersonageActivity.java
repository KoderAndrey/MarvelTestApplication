package com.example.marveltestapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.marveltestapplication.R;
import com.example.marveltestapplication.ui.base.BaseActivity;

public class PersonageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personage);
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PersonageActivity.this, MainActivity.class));
            }
        });
    }
}
