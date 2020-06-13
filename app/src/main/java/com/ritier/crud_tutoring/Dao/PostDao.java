package com.ritier.crud_tutoring.Dao;

import com.ritier.crud_tutoring.Models.Post;

import java.util.List;

public interface PostDao {

    List<Post> getPosts();

    Post getPost();

    void createPost();

    void deletePost();

    void updatePost();
}
