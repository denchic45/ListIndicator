package com.denchic45.listindicator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class ListIndicator {

    public static final String EMPTY_VIEW = "EMPTY_VIEW";
    public static final String ERROR_VIEW = "ERROR_VIEW";
    public static final String LOADING_VIEW = "LOADING_VIEW";
    public static final String NETWORK_VIEW = "NETWORK_VIEW";
    public static final String START_VIEW = "START_VIEW";
    private final RecyclerView recyclerView;
    private final ViewFlipper viewFlipper;
    private final Map<String, View> views = new HashMap<>();

    public ListIndicator(@NotNull RecyclerView recyclerView, int errorView, int loadingView, int emptyView, int networkView, int startView) {
        this.recyclerView = recyclerView;
        viewFlipper = new ViewFlipper(recyclerView.getContext());
        ViewGroup parent = (ViewGroup) recyclerView.getParent();
        viewFlipper.setLayoutParams(recyclerView.getLayoutParams());
        parent.addView(viewFlipper, parent.indexOfChild(recyclerView));
        parent.removeView(recyclerView);
        viewFlipper.addView(recyclerView);
        addView(errorView, ERROR_VIEW);
        addView(emptyView, EMPTY_VIEW);
        addView(loadingView, LOADING_VIEW);
        addView(networkView, NETWORK_VIEW);
        addView(startView, START_VIEW);
        setAnimation();
        if (recyclerView.getAdapter().getItemCount() == 0) {
            showView(START_VIEW);
        }
    }

    public void showView(String viewKey) {
        if (views.containsKey(viewKey))
            recyclerView.postDelayed(() -> viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(views.get(viewKey))), 50);
    }

    public void showList() {
        recyclerView.postDelayed(() -> viewFlipper.setDisplayedChild(0), 50);

    }

    public ListChangeListener getListChangeListener() {
        return new ListChangeListener();
    }

    private void setAnimation() {
        viewFlipper.setInAnimation(recyclerView.getContext(), R.anim.fade_in);
        viewFlipper.setOutAnimation(recyclerView.getContext(), R.anim.fade_out);
    }

    public void addView(@LayoutRes int layoutId, String viewKey) {
        if (layoutId != 0)
            addView(inflateView(layoutId), viewKey);
    }

    public void addView(View view, String viewKey) {
        if (view != null) {
            viewFlipper.addView(view);
            views.put(viewKey, view);
        }
    }

    public View getView(String viewKey) {
        return views.get(viewKey);
    }

    private View inflateView(@LayoutRes int layoutId) {
        return LayoutInflater.from(recyclerView.getContext()).inflate(layoutId, null, false);
    }

    private void checkAndShowEmptyView() {
        if (recyclerView.getAdapter().getItemCount() == 0) {
            showView(EMPTY_VIEW);
        } else {
            showList();
        }
    }

    public void listenAdapterDataObserver() {
        RecyclerView.Adapter<?> adapter = recyclerView.getAdapter();
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                checkAndShowEmptyView();
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                checkAndShowEmptyView();
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
                super.onItemRangeChanged(positionStart, itemCount, payload);
            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                checkAndShowEmptyView();
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                checkAndShowEmptyView();
            }

            @Override
            public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                checkAndShowEmptyView();
            }
        });
    }


    public static class Builder {

        private final RecyclerView recyclerView;
        private int errorView, loadingView, emptyView, networkView, startView;

        public Builder(RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
        }

        public Builder errorView(@LayoutRes int layoutId) {
            this.errorView = layoutId;
            return this;
        }


        public Builder loadingView(@LayoutRes int layoutId) {
            this.loadingView = layoutId;
            return this;
        }

        public Builder emptyView(@LayoutRes int layoutId) {
            this.emptyView = layoutId;
            return this;
        }

        public Builder networkView(@LayoutRes int layoutId) {
            this.networkView = layoutId;
            return this;
        }

        public Builder startView(@LayoutRes int layoutId) {
            this.startView = layoutId;
            return this;
        }

        public ListIndicator create() {
            return new ListIndicator(recyclerView, errorView, loadingView, emptyView, networkView, startView);
        }
    }

    private class ListChangeListener implements Runnable {

        @Override
        public void run() {
            checkAndShowEmptyView();
        }
    }
}