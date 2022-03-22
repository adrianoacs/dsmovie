package com.devsuperior.dsmovie.services;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.dto.ScoreDTO;
import com.devsuperior.dsmovie.entities.Score;
import com.devsuperior.dsmovie.entities.ScorePK;
import com.devsuperior.dsmovie.entities.User;
import com.devsuperior.dsmovie.repositories.MovieRepository;
import com.devsuperior.dsmovie.repositories.ScoreRepository;
import com.devsuperior.dsmovie.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScoreService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Transactional
    public MovieDTO saveScore(ScoreDTO scoreDTO){

        var user = userRepository.findByEmail(scoreDTO.getEmail());
        if(user == null){
            user = new User();
            user.setEmail(scoreDTO.getEmail());
            user = userRepository.saveAndFlush(user);
        }
        var movie = movieRepository.findById(scoreDTO.getMovieId()).get();

        var score = new Score();
        score.setMovie(movie);
        score.setUser(user);
        score.setValue(scoreDTO.getScore());
        score = scoreRepository.saveAndFlush(score);

        var sum = 0D;
        for(Score s : movie.getScores()){
            sum = sum + s.getValue();
        }
        var avg = sum / movie.getScores().size();
        movie.setScore(avg);
        movie.setCount(movie.getScores().size());

        movie = movieRepository.save(movie);

        return new MovieDTO(movie);
    }
}
