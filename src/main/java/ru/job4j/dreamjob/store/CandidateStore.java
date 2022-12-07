package ru.job4j.dreamjob.store;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.service.CityService;

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
