package com.ritier.crud_tutoring.Dao;

import com.ritier.crud_tutoring.Models.Post;

import java.util.List;

public interface PostDao { // Data Access Object

    List<Post> getPosts();

    Post getPostById(int id);

    void createPost(Post post);

    void deletePost(int id);

    void updatePost(Post post);
}
