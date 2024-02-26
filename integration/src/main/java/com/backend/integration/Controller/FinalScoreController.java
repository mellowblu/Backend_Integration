package com.backend.integration.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.integration.Entity.FinalScore;
import com.backend.integration.Service.FinalScoreService;


@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("http://localhost:5173")
public class FinalScoreController {

    @Autowired
    private FinalScoreService finalScoreService;

    @GetMapping("/finalscores")
    public List<FinalScore> getAllFinalScores() {
        return finalScoreService.getAllFinalScores();
    }

    @GetMapping("/finalscore/{fscore_id}")
    public ResponseEntity<FinalScore> getFinalScoreById(@PathVariable Long fscore_id) {
        Optional<FinalScore> finalScore = finalScoreService.getFinalScoreById(fscore_id);
        return finalScore.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/finalscore")
    public ResponseEntity<FinalScore> createFinalScore(@RequestBody FinalScore finalScore) {
        FinalScore createdFinalScore = finalScoreService.saveOrUpdateFinalScore(finalScore);
        return new ResponseEntity<>(createdFinalScore, HttpStatus.CREATED);
    }

    @PutMapping("/finalscore/{fscore_id}")
    public ResponseEntity<FinalScore> updateFinalScore(@PathVariable Long fscore_id, @RequestBody FinalScore finalScoreDetails) {
        Optional<FinalScore> finalScore = finalScoreService.getFinalScoreById(fscore_id);
        if (finalScore.isPresent()) {
            FinalScore updatedFinalScore = finalScoreService.saveOrUpdateFinalScore(finalScoreDetails);
            return new ResponseEntity<>(updatedFinalScore, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/finalscore/{fscore_id}")
    public ResponseEntity<Void> deleteFinalScore(@PathVariable Long fscore_id) {
        finalScoreService.deleteFinalScore(fscore_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
