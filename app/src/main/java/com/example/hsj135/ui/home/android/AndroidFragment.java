package com.example.hsj135.ui.home.android;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hsj135.R;
import com.example.hsj135.adapter.AndroidAdapter;
import com.example.hsj135.ui.home.android.AndroidFragment;
import com.example.hsj135.ui.home.android.AndroidViewModel;

public class AndroidFragment extends Fragment {

    private AndroidViewModel androidViewModel;

    public static AndroidFragment newInstance() {
        return new AndroidFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_android, container, false);
        RecyclerView recyclerView= root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(root.getContext(),DividerItemDecoration.VERTICAL));
        AndroidAdapter androidAdapter = new AndroidAdapter(null);
        recyclerView.setAdapter(androidAdapter);
        androidViewModel = new ViewModelProvider(this).get(AndroidViewModel.class);
        androidViewModel.getAndroidList().observe(getViewLifecycleOwner(), androidAdapter::setList );

        return root;
    }

}