package ru.job4j.dreamjob.service;


import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.store.PostStore;

import java.util.Collection;

@ThreadSafe
@Service
public class PostService {


    private final PostStore postStore;


    public PostService(PostStore postStore) {
        this.postStore = postStore;
    }

    public void add(Post post, City city) {
        postStore.add(post, city);
    }

    public void update(Post post, City city) {
        postStore.update(post, city);
    }

    public Post findById(int id) {
        return postStore.findById(id);
    }

    public Collection<Post> findAll() {
        return postStore.findAll();
    }


}
