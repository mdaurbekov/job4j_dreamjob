package ru.job4j.dreamjob.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.store.CandidateStore;

import java.util.Collection;
@ThreadSafe
@Service
public class CandidateService {
    private final CandidateStore candidateStore;


    public CandidateService(CandidateStore candidateStore) {
        this.candidateStore = candidateStore;
    }

    public void add(Candidate candidate, City city) {
        candidateStore.add(candidate, city);
    }

    public void update(Candidate candidate, City city) {
        candidateStore.update(candidate, city);
    }

    public Candidate findById(int id) {
        return candidateStore.findById(id);
    }

    public Collection<Candidate> findAll() {
        return candidateStore.findAll();
    }


}
