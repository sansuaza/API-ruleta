package com.masiv.masivruleta.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.ArrayList;


@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Roulette")
public class Roulette  implements Serializable {

    @Id
    private int id;
    private String state = "closed";
    private String description;

    @Autowired
    private ArrayList<Bet> betList = new ArrayList<>();

    public int getId(){
        return id;
    }

    public String getState(){
        return state;
    }
    public void open()
    {
        state = "opened";
    }
    public void close(){
        state = "closed";
    }

    public void addBet(Bet bet){
        betList.add(bet);
    }


}
