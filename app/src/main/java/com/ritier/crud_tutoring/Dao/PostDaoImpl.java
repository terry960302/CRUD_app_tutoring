package com.ritier.crud_tutoring.Dao;

import android.util.Log;
import android.widget.Toast;

import com.ritier.crud_tutoring.Models.Post;

import java.util.List;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

public class PostDaoImpl implements PostDao {

    private Realm realm;
    private String tag = "PostDaoImpl";

    public PostDaoImpl(Realm realm){
        this.realm = realm;
    }

    @Override
    public List<Post> getPosts() {
        realm.beginTransaction();
        RealmQuery<Post> query = realm.where(Post.class);
        RealmResults<Post> posts = query.findAll().sort("id", Sort.DESCENDING);
        realm.commitTransaction();

        Log.d(tag, "getPosts success");

        return realm.copyFromRealm(posts);
    }

    @Override
    public Post getPostById(int id) {
        realm.beginTransaction();
        RealmQuery<Post> query = realm.where(Post.class);
        Post post = query.equalTo("id", id).findFirst();
        realm.commitTransaction();

        Log.d(tag, "getPostById success");

        return post;
    }

    @Override
    public void createPost(final Post post) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(post);
            }
        });

        Log.d(tag, "createPost success");
    }

    @Override
    public void deletePost(final int id) {
        realm.beginTransaction();

        RealmQuery<Post> query = realm.where(Post.class);
        Post post = query.equalTo("id", id).findFirst();

       if(post != null){
           post.deleteFromRealm();
       }


        realm.commitTransaction();

        Log.d(tag, "deletePost success");
    }

    @Override
    public void updatePost(Post post) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(post);
        realm.commitTransaction();

        Log.d(tag, "updatePost success");
    }
}
