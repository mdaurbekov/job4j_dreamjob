package ru.job4j.dreamjob.store;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.service.CityService;

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
                LocalDateTime.now(), new City(1, "Москва"), true));
        posts.put(numberPost.incrementAndGet(), new Post(numberPost.get(), "Middle Java Job", "description_2",
                LocalDateTime.now(), new City(2, "СПб"), true));
        posts.put(numberPost.incrementAndGet(), new Post(numberPost.get(), "Senior Java Job", "description_3",
                LocalDateTime.now(), new City(1, "Москва"), true));
    }

    public void add(Post post, CityService cityService) {
        post.setId(numberPost.incrementAndGet());
        post.setCity(cityService.findById(post.getCity().getId()));
        posts.put(numberPost.get(), post);
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public void update(Post post, CityService cityService) {
        int id = post.getId();
        post.setCity(cityService.findById(post.getCity().getId()));
        posts.replace(id, posts.get(id), post);

    }


    public Collection<Post> findAll() {
        return posts.values();
    }
}