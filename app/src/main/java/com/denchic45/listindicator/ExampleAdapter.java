package com.denchic45.listindicator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleHolder> {

    private List<String> list;

    public ExampleAdapter(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ExampleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ExampleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleHolder holder, int position) {
        ((TextView) holder.itemView).setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ExampleHolder extends RecyclerView.ViewHolder {

        public ExampleHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
