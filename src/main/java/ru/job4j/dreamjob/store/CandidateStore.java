package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.Post;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CandidateStore {
    private static final CandidateStore INST = new CandidateStore();

    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private AtomicInteger numberCandidate = new AtomicInteger();

    private CandidateStore() {
        candidates.put(numberCandidate.incrementAndGet(), new Candidate(numberCandidate.get(), "Ivanov Ivan", "description_candidate_1", LocalDateTime.now()));
        candidates.put(numberCandidate.incrementAndGet(), new Candidate(numberCandidate.get(), "Petrov Petr", "description_candidate_2", LocalDateTime.now()));
        candidates.put(numberCandidate.incrementAndGet(), new Candidate(numberCandidate.get(), "Stepanov Stepan", "description_candidate_3", LocalDateTime.now()));
    }

    public void add(Candidate candidate) {
        candidate.setId(numberCandidate.incrementAndGet());
        candidates.put(numberCandidate.get(), candidate);
    }

    public Candidate findById(int id) {
        return candidates.get(id);
    }

    public void update(Candidate candidate) {
        int id = candidate.getId();
        candidates.replace(id, candidates.get(id), candidate);

    }
    public static CandidateStore instOf() {
        return INST;
    }

    public Collection<Candidate> findAll() {
        return candidates.values();
    }
}
