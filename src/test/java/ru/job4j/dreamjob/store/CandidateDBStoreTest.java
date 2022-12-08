package ru.job4j.dreamjob.store;

import org.junit.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.City;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CandidateDBStoreTest {
    CandidateDBStore store = new CandidateDBStore(new Main().loadPool());

    @Test
    public void whenCreateCandidate() {
        Candidate candidate = new Candidate(1, "candidate 1", "description_1",
                LocalDateTime.now(), true, new City(1, "Москва"), null);
        store.add(candidate);
        Candidate candidateInDb = store.findById(candidate.getId());
        assertThat(candidateInDb.getName(), is(candidate.getName()));
    }

    @Test
    public void updateCandidate() {
        Candidate candidate1 = new Candidate(1, "candidate 1", "description_1",
                LocalDateTime.now(), true, new City(1, "Москва"), null);
        Candidate candidate2 = new Candidate(1, "candidate 1 update", "description_1",
                LocalDateTime.now(), true, new City(1, "Москва"), null);
        store.add(candidate1);
        store.update(candidate2);
        Candidate candidateInDb = store.findById(candidate2.getId());
        assertThat(candidateInDb.getName(), is(candidate2.getName()));
    }
}