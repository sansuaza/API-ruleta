package com.masiv.masivruleta.repository;



import com.masiv.masivruleta.entity.Roulette;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.util.List;
import java.util.UUID;

@Repository
public class RouletteDao {
    public static final String HASH_KEY = "Roulette";
    @Autowired
    private RedisTemplate template;

    public Roulette save(Roulette roulette){
        template.opsForHash().put(HASH_KEY,roulette.getId(), roulette);
        return roulette;
    }

    public List<Roulette> findAll(){
       return template.opsForHash().values(HASH_KEY);
    }

    public void openRoulette(int idRoulette){
        Roulette roulette =  (Roulette) template.opsForHash().get(HASH_KEY, idRoulette);
        if (roulette != null) {
            if (roulette.getState().equals("closed")) {
                changeState(roulette, "open");
            }
        }
    }
    public void closeRoulette(int idRoulette){
        Roulette roulette =  (Roulette) template.opsForHash().get(HASH_KEY, idRoulette);
        if (roulette != null) {
            if (roulette.getState().equals("opened")) {
                changeState(roulette, "close");
            }
        }
    }

    private void changeState(Roulette roulette, String action){

        switch (action) {
            case "close":
                roulette.close();
                break;
            case "open":
                roulette.open();
                break;
            default:

        }
        save(roulette);
    }


    public void makeBet(int number, int amountMoney){

    }



    public String remove(int id){
        template.opsForHash().delete(HASH_KEY, id);
        return "deleted";
    }


}
