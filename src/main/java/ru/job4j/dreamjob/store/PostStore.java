package ru.job4j.dreamjob.store;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
@Repository
public class PostStore {


    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private final AtomicInteger numberPost = new AtomicInteger();

    private PostStore() {
        posts.put(numberPost.incrementAndGet(), new Post(numberPost.get(), "Junior Java Job", "description_1",
                LocalDateTime.now(), new City(1, "Москва")));
        posts.put(numberPost.incrementAndGet(), new Post(numberPost.get(), "Middle Java Job", "description_2",
                LocalDateTime.now(), new City(2, "СПб")));
        posts.put(numberPost.incrementAndGet(), new Post(numberPost.get(), "Senior Java Job", "description_3",
                LocalDateTime.now(), new City(1, "Москва")));
    }

    public void add(Post post, City city) {
        post.setId(numberPost.incrementAndGet());
        post.setCity(city);
        posts.put(numberPost.get(), post);
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public void update(Post post, City city) {
        int id = post.getId();
        post.setCity(city);
        posts.replace(id, posts.get(id), post);

    }


    public Collection<Post> findAll() {
        return posts.values();
    }
}