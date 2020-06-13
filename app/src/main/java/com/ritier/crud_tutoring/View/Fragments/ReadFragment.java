package com.ritier.crud_tutoring.View.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ritier.crud_tutoring.R;
import com.ritier.crud_tutoring.View.PostItem;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.GroupieViewHolder;

public class ReadFragment extends Fragment {

    RecyclerView rvPosts;
    GroupAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read, container, false);

        rvPosts = view.findViewById(R.id.rv_posts);
        rvPosts.setLayoutManager(new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false));
        adapter = new GroupAdapter();


        for(int i=0; i<= 20; i++){
            adapter.add(new PostItem(1, "Asdasd", "ASdasda"));
        }

        rvPosts.setAdapter(adapter);

        return view;
    }
}
