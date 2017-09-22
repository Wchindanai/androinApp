package com.example.dream.kucassava;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dream on 9/11/17.
 */

public class ItemTypeAdapter extends RecyclerView.Adapter<ItemTypeAdapter.ViewHolder> {
    private Context context;
    private List<String> listType;
    private List<MarketModel> marketModelList;

    public ItemTypeAdapter(Context context, List<String> listType, List<MarketModel> marketModelList) {
        this.context = context;
        this.listType = listType;
        this.marketModelList = marketModelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.type.setText(listType.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Market.class);
                Bundle bundle = new Bundle();
                List<MarketModel> marketModels = new ArrayList<MarketModel>();
                for (int i = 0; i < marketModelList.size(); i++){
                    MarketModel marketModel = marketModelList.get(i);
                    if(marketModel.getProductIndex() == position){
                        marketModels.add(marketModel);
                    }
                }
                bundle.putSerializable("markets", (Serializable) marketModels);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listType.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView type;
        public ViewHolder(View itemView) {
            super(itemView);
            type = (TextView) itemView.findViewById(R.id.itemType);
        }
    }
}
