package com.example.dream.kucassava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by dream on 6/7/17.
 */

public class MenuPage extends AppCompatActivity {
    private ImageButton location, landPage, saveGrowPage, valuePage, profilePage, exitPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        location = (ImageButton) findViewById(R.id.locationSelected);
        landPage = (ImageButton) findViewById(R.id.landSelected);
        saveGrowPage = (ImageButton) findViewById(R.id.saveGrowDetail);
        valuePage = (ImageButton) findViewById(R.id.value);
        profilePage = (ImageButton) findViewById(R.id.userDetail);
        exitPage = (ImageButton) findViewById(R.id.exit);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), TabActivity.class);
                intent.putExtra("position", 0);
                startActivity(intent);
            }
        });

        landPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), TabActivity.class);
                intent.putExtra("position", 1);
                startActivity(intent);
            }
        });

        saveGrowPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), TabActivity.class);
                intent.putExtra("position", 2);
                startActivity(intent);
            }
        });

        valuePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), TabActivity.class);
                intent.putExtra("position", 3);
                startActivity(intent);
            }
        });

        profilePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), TabActivity.class);
                intent.putExtra("position", 4);
                startActivity(intent);
            }
        });
    }
}
