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
 * Created by dream on 9/11/17.
 */

public class ItemTypeAdapter extends RecyclerView.Adapter<ItemTypeAdapter.ViewHolder> {
    private Context context;
    private List<String> listType;

    public ItemTypeAdapter(Context context, List<String> listType) {
        this.context = context;
        this.listType = listType;
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
                intent.putExtra("position", position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listType.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView type;
        public ViewHolder(View itemView) {
            super(itemView);
            type = (TextView) itemView.findViewById(R.id.itemType);
        }
    }
}
