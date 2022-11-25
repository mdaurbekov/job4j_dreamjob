package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Post;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class PostStore {

    private static final PostStore INST = new PostStore();

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private final AtomicInteger numberPost = new AtomicInteger();

    private PostStore() {
        posts.put(numberPost.incrementAndGet(), new Post(numberPost.get(), "Junior Java Job", "description_1", LocalDateTime.now()));
        posts.put(numberPost.incrementAndGet(), new Post(numberPost.get(), "Middle Java Job", "description_2", LocalDateTime.now()));
        posts.put(numberPost.incrementAndGet(), new Post(numberPost.get(), "Senior Java Job", "description_3", LocalDateTime.now()));
    }

    public void add(Post post) {
        post.setId(numberPost.incrementAndGet());
        posts.put(numberPost.get(), post);
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public void update(Post post) {
        int id = post.getId();
        posts.replace(id, posts.get(id), post);

    }

    public static PostStore instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }
}