package com.pudge.lollipopmaterial;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DotaHeroDetailsActivity extends AppCompatActivity {

    public static final String POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dota_hero_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent intent = getIntent();

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingtoolbar);
        collapsingToolbarLayout.setTitle(DotaHeros.sHerosName[intent.getIntExtra(POSITION, 0)]);

        ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load(DotaHeros.iHerosIcon[intent.getIntExtra(POSITION, 0)]).centerCrop().into(imageView);

        TextView heroInfo = (TextView) findViewById(R.id.dota_hero_info);
        heroInfo.setText(DotaHeros.mHerosInfo[intent.getIntExtra(POSITION, 0)]);

        TextView heroUsage = (TextView) findViewById(R.id.dota_hero_usage);
        heroUsage.setText(DotaHeros.mHerosUsage[intent.getIntExtra(POSITION, 0)]);

        TextView heroFriends = (TextView) findViewById(R.id.dota_hero_friends);
        heroFriends.setText(DotaHeros.mHerosFriends[intent.getIntExtra(POSITION, 0)]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
