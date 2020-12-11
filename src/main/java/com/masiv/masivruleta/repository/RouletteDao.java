package com.masiv.masivruleta.repository;



import com.masiv.masivruleta.entity.Bet;
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
        Roulette roulette =  findRouletteById(idRoulette);
        if (roulette != null) {
            if (roulette.getState().equals("closed")) {
                changeState(roulette, "open");
            }
        }
    }
    public void closeRoulette(int idRoulette){
        Roulette roulette =  findRouletteById(idRoulette);
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

    public String addBet(int idRoulette, Bet bet, int userId){
        Roulette roulette = findRouletteById(idRoulette);
        if (roulette != null){
            if (roulette.getState().equals("opened")){
                if (reviewBetData(bet)){
                    bet.setIdUser(userId);
                    roulette.addBet(bet);
                    save(roulette);
                    return "Bet made";
                }

            }
            else
            {
                return "Roulette's state is not opened";
            }
        }
        else
        {
            return "Roulette Not Found";
        }

        return  null;
    }

    private boolean reviewBetData(Bet bet){
        if((0 < bet.getBetNumber() && bet.getBetNumber() < 36) && bet.getBetAmount() < 10000){
            return true;
        }
        return false;
    }

    public void makeBet(int number, int amountMoney){

    }



    public String remove(int id){
        template.opsForHash().delete(HASH_KEY, id);
        return "deleted";
    }

    private Roulette findRouletteById(int id){
        return  (Roulette) template.opsForHash().get(HASH_KEY, id);
    }


}
