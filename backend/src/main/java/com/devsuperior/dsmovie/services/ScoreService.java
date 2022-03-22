package com.devsuperior.dsmovie.services;

import com.devsuperior.dsmovie.dto.ScoreDTO;
import com.devsuperior.dsmovie.entities.Score;
import com.devsuperior.dsmovie.entities.ScorePK;
import com.devsuperior.dsmovie.repositories.MovieRepository;
import com.devsuperior.dsmovie.repositories.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScoreService {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Transactional
    public void saveScore(ScoreDTO scoreDTO){

        var user = userService.save(scoreDTO.getEmail());
        var movie = movieRepository.findById(scoreDTO.getMovieId()).get();
        var score = new Score();
        var scorePK = new ScorePK(movie, user);
        score.setId(scorePK);
        score.setValue(scoreDTO.getScore());
        scoreRepository.save(score);

        movie = movieRepository.findById(scoreDTO.getMovieId()).get();
        var totalScores = movie.getScores().stream()
                .map(s -> s.getValue())
                .reduce(0.0, (a, b) -> a + b);
        movie.setCount(movie.getScores().size());
        movie.setScore(totalScores / movie.getScores().size());
        movieRepository.save(movie);
    }
}
