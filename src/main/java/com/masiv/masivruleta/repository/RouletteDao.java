package com.masiv.masivruleta.repository;



import com.masiv.masivruleta.entity.Bet;
import com.masiv.masivruleta.entity.Roulette;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import sun.plugin2.message.Message;

import javax.swing.*;
import java.util.HashMap;
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

    public ResponseEntity openRoulette(int idRoulette){
        Roulette roulette =  findRouletteById(idRoulette);
        if (roulette != null) {
            if (roulette.getState().equals("closed")) {
                changeState(roulette, "open");
                return new ResponseEntity(HttpStatus.OK);
            }
            else {
                return new ResponseEntity("The Roulette has been opened before", HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity closeRoulette(int idRoulette){
        Roulette roulette =  findRouletteById(idRoulette);
        if (roulette != null) {
            if (roulette.getState().equals("opened")) {
                changeState(roulette, "close");
                return new ResponseEntity(generateUserWinners(generateWinnerNumber(roulette)), HttpStatus.OK);
            }
            else {
                return new ResponseEntity("The roulette is already closed", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    private Roulette generateWinnerNumber(Roulette roulette){
        int winnerNumber = (int) Math.floor(Math.random()*36+1);
        roulette.setWinnerNumber(winnerNumber);
        return roulette;
    }

    private Roulette generateUserWinners(Roulette roulette){
        for (Bet bet : roulette.getBetList()){
            if (bet.getTypeBet().equals("number")){
                if (bet.getBetNumber() == roulette.getWinnerNumber())
                {
                    bet.setBetPrize(bet.getBetAmount()*5);
                    bet.setWinner(true);
                }
            }else
            {
                if (bet.getBetNumber()%2 == 0&& roulette.getWinnerNumber()%2 == 0 ||
                        bet.getBetNumber()%2 != 0&& roulette.getWinnerNumber()%2 != 0 )
                {
                    bet.setBetPrize((long) Math.floor(bet.getBetAmount()*1.8));
                }
            }
        }
        save(roulette);
        return roulette;
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

    public ResponseEntity addBet(int idRoulette, Bet bet, int userId){
        Roulette roulette = findRouletteById(idRoulette);
        if (roulette != null){
            if (roulette.getState().equals("opened")){
                if (reviewBetData(bet)){
                    bet.setIdUser(userId);
                    roulette.addBet(bet);
                    save(roulette);
                    return new ResponseEntity(HttpStatus.OK);
                }
            }
            else
            {
                return new ResponseEntity("The roulette is disable to make a bet", HttpStatus.BAD_REQUEST);
            }
        }

         return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    private boolean reviewBetData(Bet bet){
        if((0 < bet.getBetNumber() && bet.getBetNumber() < 36) && bet.getBetAmount() < 10000){
            return true;
        }

        return false;
    }

    public String remove(int id){
        template.opsForHash().delete(HASH_KEY, id);
        return "deleted";
    }

    private Roulette findRouletteById(int id){
        return  (Roulette) template.opsForHash().get(HASH_KEY, id);
    }

}
