package com.masiv.masivruleta.entity;

import io.netty.buffer.search.SearchProcessor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bet implements Serializable {


    private int idUser;
    private String typeBet;
    private long betAmount;
    private int betNumber;
    private int winnerNumber;

    public int getIdUser() {
        return idUser;
    }

    public String getTypeBet() {
        return typeBet;
    }

    public long getBetAmount() {
        return betAmount;
    }

    public int getBetNumber() {
        return betNumber;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setTypeBet(String typeBet) {
        this.typeBet = typeBet;
    }

    public void setBetAmount(long betAmount) {
        this.betAmount = betAmount;
    }

    public void setBetNumber(int betNumber) {
        this.betNumber = betNumber;
    }
}
