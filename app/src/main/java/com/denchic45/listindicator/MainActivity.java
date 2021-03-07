package com.denchic45.listindicator;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnEmpty = findViewById(R.id.btn_empty);
        Button btnFill = findViewById(R.id.btn_fill);
        RecyclerView rv = findViewById(R.id.rv);
        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        ExampleListAdapter adapter = new ExampleListAdapter();
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        ListIndicator listIndicator = new ListIndicator.Builder(rv)
                .emptyView(R.layout.view_empty)
                .startView(R.layout.view_start)
                .errorView(R.layout.view_error)
                .create();

        viewModel.showList.observe(this, list -> adapter.submitList(list, listIndicator.getListChangeListener()));


        btnEmpty.setOnClickListener(v -> viewModel.onEmptyCLick());

        btnFill.setOnClickListener(v -> viewModel.onFillCLick());

        listIndicator.getView(ListIndicator.ERROR_VIEW).setOnClickListener(v -> {
            listIndicator.showView(ListIndicator.EMPTY_VIEW);
            listIndicator.getView(ListIndicator.EMPTY_VIEW).setOnClickListener(v1 -> listIndicator.showList());
        });
        listIndicator.listenAdapterDataObserver();
    }
}