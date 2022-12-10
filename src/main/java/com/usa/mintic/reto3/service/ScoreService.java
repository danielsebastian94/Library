package com.usa.mintic.reto3.service;


import com.usa.mintic.reto3.model.Score;
import com.usa.mintic.reto3.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;
    public List<Score> getAll(){
        return scoreRepository.getAll();
    }

    public Optional<Score> getScore(int id){
        return scoreRepository.getScore(id);
    }

    public Score save(Score m){
        if(m.getIdScore()==null){
            return scoreRepository.save(m);
        }else{
            Optional<Score> e = scoreRepository.getScore(m.getIdScore());
            if(e.isPresent()){
                return m;
            }else {
                return scoreRepository.save(m);
            }
        }

    }

    public Score update(Score m){
        if (m.getIdScore()!=null){
            Optional<Score> e = scoreRepository.getScore(m.getIdScore());
            if(e.isPresent()){
                if (m.getScore()!= null){
                    e.get().setScore(m.getScore());
                }
                scoreRepository.save(e.get());
                return e.get();
            }else{
                return m;
            }
        }else{
            return m;
        }
    }

    public boolean delete(int id){
        Optional<Score> m = scoreRepository.getScore(id);
        boolean flag = false;
        if (m.isPresent()){
            scoreRepository.delete(m.get());
            flag = true;
        }
        return flag;
    }
}
