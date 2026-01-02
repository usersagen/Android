package com.example.hsj135.ui.home.php;

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
import com.example.hsj135.adapter.PhpAdapter;
import com.example.hsj135.ui.home.php.PhpFragment;
import com.example.hsj135.ui.home.php.PhpViewModel;

public class PhpFragment extends Fragment {

    private PhpViewModel phpViewModel;

    public static PhpFragment newInstance() {
        return new PhpFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_php, container, false);
        RecyclerView recyclerView= root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(root.getContext(),DividerItemDecoration.VERTICAL));
        PhpAdapter phpAdapter = new PhpAdapter(null);
        recyclerView.setAdapter(phpAdapter);
        phpViewModel = new ViewModelProvider(this).get(PhpViewModel.class);
        phpViewModel.getPhpList().observe(getViewLifecycleOwner(), phpAdapter::setList );

        return root;
    }

}