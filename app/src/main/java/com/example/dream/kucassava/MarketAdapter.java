package com.example.dream.kucassava;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dream on 22/9/17.
 */

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.ViewHolder> {
    private Context context;
    private List<MarketModel> marketModelList;

    public MarketAdapter(Context context, List<MarketModel> marketModelList) {
        this.context = context;
        this.marketModelList = marketModelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.type.setText(marketModelList.get(position).getMarketName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SummaryPrice.class);
                intent.putExtra("marketName", marketModelList.get(position).getMarketName());
                intent.putExtra("price", marketModelList.get(position).getPrice());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return marketModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView type;
        public ViewHolder(View itemView) {
            super(itemView);
            type = (TextView) itemView.findViewById(R.id.itemType);
        }
    }
}
