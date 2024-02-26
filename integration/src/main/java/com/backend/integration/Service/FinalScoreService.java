package com.backend.integration.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.integration.Entity.FinalScore;
import com.backend.integration.Repo.FinalScoreRepository;

@Service
public class FinalScoreService {

    @Autowired
    private FinalScoreRepository finalScoreRepository;

    public List<FinalScore> getAllFinalScores() {
        return finalScoreRepository.findAll();
    }

    public Optional<FinalScore> getFinalScoreById(Long fscore_id) {
        return finalScoreRepository.findById(fscore_id);
    }

    public FinalScore saveOrUpdateFinalScore(FinalScore finalScore) {
        return finalScoreRepository.save(finalScore);
    }

    public void deleteFinalScore(Long fscore_id) {
        finalScoreRepository.deleteById(fscore_id);
    }

}
