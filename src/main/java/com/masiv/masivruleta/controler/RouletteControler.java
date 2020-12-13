package com.masiv.masivruleta.controler;

import com.masiv.masivruleta.entity.Bet;
import com.masiv.masivruleta.entity.Roulette;
import com.masiv.masivruleta.repository.RouletteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/roulette")
public class RouletteControler {

    @Autowired
    private RouletteDao rouletteDao;

    @PostMapping
    public ResponseEntity save(@RequestBody Roulette roulette){
        Roulette roulette1 = rouletteDao.save(roulette);
        HashMap idRoulette = new HashMap<>();
        idRoulette.put("id", roulette1.getId());
        return new ResponseEntity<>(idRoulette, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Roulette>> listRoulettes(){
        return new ResponseEntity<>(rouletteDao.findAll(), HttpStatus.OK);
    }

    @PostMapping("/open/{id}")
    public ResponseEntity openRoullete(@PathVariable int id){
        return rouletteDao.openRoulette(id);
    }

    @PostMapping("/close/{id}")
    public ResponseEntity closeRoullete(@PathVariable int id){
        return rouletteDao.closeRoulette(id);
    }

    @DeleteMapping("/{id}")
    public String deleteRoulette(@PathVariable int id){
        return rouletteDao.remove(id);
    }

    @PostMapping("/bet/{idRoulette}")
    public ResponseEntity makeBet(@PathVariable int idRoulette, @RequestBody Bet bet, @RequestHeader int userId){
        return rouletteDao.addBet(idRoulette, bet, userId);
    }
}
