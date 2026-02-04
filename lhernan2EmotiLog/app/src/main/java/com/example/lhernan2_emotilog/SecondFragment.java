package com.example.lhernan2_emotilog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;

public class SecondFragment extends Fragment {

    private EmotionViewModel viewModel;
    private LogArrayAdapter adapter;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        View view = inflater.inflate(R.layout.fragment_second, container, false);

        viewModel = new ViewModelProvider(requireActivity())
                .get(EmotionViewModel.class);

        ListView list = view.findViewById(R.id.log_list_view);
        adapter = new LogArrayAdapter(requireContext(), new ArrayList<>());
        list.setAdapter(adapter);

        viewModel.getLogs().observe(getViewLifecycleOwner(), logs -> {
            adapter.clear();
            adapter.addAll(logs);
            adapter.notifyDataSetChanged();
        });

        return view;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        view.findViewById(R.id.back_btn_log).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this).navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

}