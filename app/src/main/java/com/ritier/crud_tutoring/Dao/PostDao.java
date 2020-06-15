package com.ritier.crud_tutoring.Dao;

import com.ritier.crud_tutoring.Models.Post;

import java.util.List;

public interface PostDao { // Data Access Object

    //Read all
    List<Post> getPosts();

    //Read one
    Post getPostById(int id);

    //Create
    void createPost(Post post);

    //Delete
    void deletePost(int id);

    //Update
    void updatePost(Post post);
}
