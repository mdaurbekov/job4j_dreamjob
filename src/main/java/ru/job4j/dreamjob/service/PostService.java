package ru.job4j.dreamjob.service;


import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.store.PostStore;

import java.util.Collection;

public class PostService {

    private static final PostService POST_SERVICE = new PostService();


    private final PostStore postStore = PostStore.instOf();

    public static PostService instOf() {
        return POST_SERVICE;
    }

    public void add(Post post) {
        postStore.add(post);
    }

    public void update(Post post) {
        postStore.update(post);
    }

    public Post findById(int id) {
        return postStore.findById(id);
    }

    public Collection<Post> findAll() {
        return postStore.findAll();
    }


}
