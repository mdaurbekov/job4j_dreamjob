package ru.job4j.dreamjob.store;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.service.CityService;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
@Repository
public class CandidateStore {


    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private AtomicInteger numberCandidate = new AtomicInteger();
    private final CityService cityService;


    private CandidateStore(CityService cityService) {
        this.cityService = cityService;
        candidates.put(numberCandidate.incrementAndGet(), new Candidate(numberCandidate.get(), "Ivanov Ivan", "description_candidate_1",
                LocalDateTime.now(), new City(1, "Москва")));
        candidates.put(numberCandidate.incrementAndGet(), new Candidate(numberCandidate.get(), "Petrov Petr", "description_candidate_2",
                LocalDateTime.now(), new City(1, "Москва")));
        candidates.put(numberCandidate.incrementAndGet(), new Candidate(numberCandidate.get(), "Stepanov Stepan", "description_candidate_3",
                LocalDateTime.now(), new City(1, "Москва")));
    }

    public void add(Candidate candidate) {
        candidate.setId(numberCandidate.incrementAndGet());
        candidate.setCity(cityService.findById(candidate.getCity().getId()));
        candidates.put(numberCandidate.get(), candidate);
    }

    public Candidate findById(int id) {
        return candidates.get(id);
    }

    public void update(Candidate candidate) {
        int id = candidate.getId();
        candidate.setCity(cityService.findById(candidate.getCity().getId()));
        candidates.replace(id, candidates.get(id), candidate);

    }


    public Collection<Candidate> findAll() {
        return candidates.values();
    }
}
