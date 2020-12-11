package com.masiv.masivruleta.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Roulette")
public class Roulette  implements Serializable {

    @Id
    private int id;
    private String state = "closed";
    private String description;

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


}
