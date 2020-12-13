package com.masiv.masivruleta;

import com.masiv.masivruleta.entity.Bet;
import com.masiv.masivruleta.entity.Roulette;
import com.masiv.masivruleta.repository.RouletteDao;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@SpringBootApplication
public class MasivRuletaApplication {


	public static void main(String[] args) {
		SpringApplication.run(MasivRuletaApplication.class, args);
	}

}
