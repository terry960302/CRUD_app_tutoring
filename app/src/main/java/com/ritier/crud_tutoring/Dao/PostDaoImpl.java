package com.ritier.crud_tutoring.Dao;

import com.ritier.crud_tutoring.Models.Post;

import java.util.List;

import io.realm.Realm;

public class PostDaoImpl implements PostDao {

    private Realm realm;

    public PostDaoImpl(Realm realm){
        this.realm = realm;
    }

    @Override
    public List<Post> getPosts() {

        return null;
    }

    @Override
    public Post getPost() {
        return null;
    }

    @Override
    public void createPost() {

    }

    @Override
    public void deletePost() {

    }

    @Override
    public void updatePost() {

    }
}
