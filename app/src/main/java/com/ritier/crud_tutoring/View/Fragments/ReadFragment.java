package com.ritier.crud_tutoring.View.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ritier.crud_tutoring.Dao.PostDaoImpl;
import com.ritier.crud_tutoring.Models.Post;
import com.ritier.crud_tutoring.R;
import com.ritier.crud_tutoring.Utils;
import com.ritier.crud_tutoring.View.PostItem;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.GroupieViewHolder;

import java.util.List;

import io.realm.Realm;

public class ReadFragment extends Fragment {

    private RecyclerView rvPosts;
    private GroupAdapter adapter;
    private PostDaoImpl postDao;
    private Realm realm = Realm.getDefaultInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read, container, false);

        initRecyclerView(view);

        return view;
    }

    private void initRecyclerView(View view){
        rvPosts = view.findViewById(R.id.rv_posts);
        rvPosts.setLayoutManager(new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false));
        adapter = new GroupAdapter();

        for(int i = 0; i < getPostListFromRealm().size(); i++){
            Post post = getPostListFromRealm().get(i);

            PostItem item  = new PostItem(post.getId(), post.getTitle(), post.getDesc());

            adapter.add(item);
        }

        rvPosts.setAdapter(adapter);
    }

    private List<Post> getPostListFromRealm(){
        postDao = new PostDaoImpl(realm);
        return postDao.getPosts();
    }


}
