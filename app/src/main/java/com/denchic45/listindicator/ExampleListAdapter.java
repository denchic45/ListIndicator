package com.denchic45.listindicator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExampleListAdapter extends ListAdapter< String,ExampleListAdapter.ExampleHolder> {


    public ExampleListAdapter() {
        super(new DiffUtil.ItemCallback<String>() {
            @Override
            public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
                return oldItem.equals(newItem);
            }
        });
    }

    @NonNull
    @Override
    public ExampleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ExampleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleHolder holder, int position) {
        ((TextView) holder.itemView).setText(getItem(position));
    }


    public static class ExampleHolder extends RecyclerView.ViewHolder {

        public ExampleHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
