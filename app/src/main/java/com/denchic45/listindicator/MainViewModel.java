package com.denchic45.listindicator;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainViewModel extends ViewModel {

    private final List<String> list;
    public final MutableLiveData<List<String>> showList = new MutableLiveData<>();

    public MainViewModel() {
        list = new ArrayList<>(Arrays.asList(
                "123", "456", "789", "123", "456", "789",
                "123", "456", "789",
                "123", "456", "789",
                "123", "456", "789",
                "123", "456", "789",
                "123", "456", "789",
                "123", "456", "789"));
    }

    public void onEmptyCLick() {
        showList.setValue(Collections.emptyList());
    }

    public void onFillCLick() {
        showList.setValue(list);
    }
}
