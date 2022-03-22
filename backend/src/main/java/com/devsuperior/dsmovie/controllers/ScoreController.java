package com.devsuperior.dsmovie.controllers;

import com.devsuperior.dsmovie.dto.ScoreDTO;
import com.devsuperior.dsmovie.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scores")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @PutMapping("scoreMovie")
    public void saveScore(@RequestBody ScoreDTO scoreDTO){
        scoreService.saveScore(scoreDTO);
    }

    @GetMapping
    public String getScore(){
        return "operação get não permitida";
    }
}
