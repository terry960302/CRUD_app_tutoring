package com.ritier.crud_tutoring.View.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ritier.crud_tutoring.Dao.PostDaoImpl;
import com.ritier.crud_tutoring.Models.Post;
import com.ritier.crud_tutoring.R;
import com.ritier.crud_tutoring.View.Adapters.PostRecyAdapter;
import com.ritier.crud_tutoring.View.Items.PostItem;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class ReadFragment extends Fragment {

    private RecyclerView rvPosts;
    private PostRecyAdapter adapter;
    private PostDaoImpl postDao;
    private Realm realm = Realm.getDefaultInstance();
    private List<Post> posts = new ArrayList<Post>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read, container, false);

        postDao = new PostDaoImpl(realm);
        initRecyclerView(view);

        return view;
    }

    private void initRecyclerView(View view){
        rvPosts = view.findViewById(R.id.rv_posts);
        rvPosts.setLayoutManager(new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false));

        adapter = new PostRecyAdapter(this.getContext(), posts, postDao);

        addPosts2Adapter();

        rvPosts.setAdapter(adapter);
    }

    private void addPosts2Adapter(){
        adapter.posts = new ArrayList<>();
        for(int i = 0; i < getPostListFromRealm().size(); i++){
            Post post = getPostListFromRealm().get(i);
            adapter.addItem(post);
        }
    }

    private List<Post> getPostListFromRealm(){
        posts =  postDao.getPosts();
        return posts;
    }




}
