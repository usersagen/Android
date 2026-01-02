package com.example.hsj135.ui.home.java;

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
import com.example.hsj135.adapter.JavaAdapter;
import com.example.hsj135.ui.home.java.JavaFragment;
import com.example.hsj135.ui.home.java.JavaViewModel;

public class JavaFragment extends Fragment {

    private JavaViewModel javaViewModel;

    public static JavaFragment newInstance() {
        return new JavaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_java, container, false);
        RecyclerView recyclerView= root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(root.getContext(),DividerItemDecoration.VERTICAL));
        JavaAdapter javaAdapter = new JavaAdapter(null);
        recyclerView.setAdapter(javaAdapter);
        javaViewModel = new ViewModelProvider(this).get(JavaViewModel.class);
        javaViewModel.getJavaList().observe(getViewLifecycleOwner(), javaAdapter::setList );

        return root;
    }

}