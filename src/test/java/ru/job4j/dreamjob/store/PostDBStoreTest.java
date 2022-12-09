package ru.job4j.dreamjob.store;

import org.junit.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class PostDBStoreTest {
    PostDBStore store = new PostDBStore(new Main().loadPool());

    @Test
    public void whenCreatePost() {
        Post post = new Post(1, "Junior Java Job 1", "description_1",
                LocalDateTime.now(), new City(1, "Москва"), true);
        store.add(post);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }

    @Test
    public void updatePost() {
        Post post1 = new Post(2, "Junior Java Job 2", "description_2",
                LocalDateTime.now(), new City(1, "Москва"), true);
        Post post2 = new Post(2, "Junior Java Job 2 update", "description_2",
                LocalDateTime.now(), new City(1, "Москва"), true);
        store.add(post1);
        store.update(post2);
        Post postInDb = store.findById(post2.getId());
        assertThat(postInDb.getName(), is(post2.getName()));
    }
}