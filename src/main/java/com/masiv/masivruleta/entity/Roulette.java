package com.masiv.masivruleta.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Roulette")
public class Roulette {
    public int getId() {
        return id;
    }

    @Id
    private int id;
    private boolean oppend = false;


}
