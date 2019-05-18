package com.example.programmingknowledge.mybalance_v11;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SearchFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search,container,false);

    }

    public void MoveToBalanceList(View view){
        Intent intent = new Intent();
        intent.setClass(getActivity(), BalanceListActivity.class);
        startActivity(intent);
    }
}
