package com.example.dream.kucassava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Market extends AppCompatActivity {
    private RecyclerView recyclerView;
    private static final String TAG = "Market";
    private List<MarketModel> marketModelList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marget);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        Bundle bundle = getIntent().getExtras();
        marketModelList = (ArrayList<MarketModel>) bundle.getSerializable("markets");

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(getApplication()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        MarketAdapter marketAdapter = new MarketAdapter(getApplication(), marketModelList);
        recyclerView.setAdapter(marketAdapter);
    }
}
