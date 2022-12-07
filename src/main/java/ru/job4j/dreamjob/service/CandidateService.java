package ru.job4j.dreamjob.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.store.CandidateDBStore;

import java.util.Collection;
import java.util.List;

@ThreadSafe
@Service
public class CandidateService {
    private final CandidateDBStore candidateStore;
    private final CityService cityService;


    public CandidateService(CandidateDBStore candidateStore, CityService cityService) {
        this.candidateStore = candidateStore;
        this.cityService = cityService;
    }

    public void add(Candidate candidate) {
        candidateStore.add(candidate);
    }

    public void update(Candidate candidate) {
        candidateStore.update(candidate);
    }

    public Candidate findById(int id) {
        Candidate candidate = candidateStore.findById(id);
        candidate.setCity(cityService.findById(candidate.getCity().getId()));
        return candidate;
    }

    public Collection<Candidate> findAll() {
        List<Candidate> candidates = candidateStore.findAll();
        candidates.forEach(
                candidate -> candidate.setCity(
                        cityService.findById(candidate.getCity().getId()))
        );
        return candidates;
    }


}
