package com.example.newsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Health extends Fragment {

    private String category="health";
    String api="278bc349b08447cbb1b26c45a18a8a2a";
    ArrayList<ModelClass> modelClassArrayList;
    Adapter adapter;
    String country="in";
    private RecyclerView recyclerViewOfHealth;
    MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.health_fragment, null);

        recyclerViewOfHealth=v.findViewById(R.id.recycler_view_health);
        modelClassArrayList=new ArrayList<>();
        recyclerViewOfHealth.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new Adapter(getContext(), modelClassArrayList);
        recyclerViewOfHealth.setAdapter(adapter);
        mainActivity=(MainActivity)getActivity();

        findNews();
        final int[] state=new int[1];

        recyclerViewOfHealth.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                state[0]=newState;

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy>0 && (state[0]==0 || state[0]==2)){
                    hideToolbar();
                }
                else if(dy<-10){
                    showToolbar();
                }

            }
        });
        return v;
    }

    private void hideToolbar(){
        mainActivity.toolbar_layout.setVisibility(View.GONE);
    }
    private void showToolbar() {
        mainActivity.toolbar_layout.setVisibility(View.VISIBLE);
    }

    private void findNews() {
        ApiUtilities.getApiInterface().getCategoryNews(country, category, 100, api).enqueue(new Callback<ModelClass2>() {
            @Override
            public void onResponse(Call<ModelClass2> call, Response<ModelClass2> response) {
                if (response.isSuccessful()) {
                    modelClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ModelClass2> call, Throwable t) {

            }
        });
    }
}
