package com.masiv.masivruleta;

import com.masiv.masivruleta.entity.Roulette;
import com.masiv.masivruleta.repository.RouletteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/roulette")
public class MasivRuletaApplication {
	@Autowired
	private RouletteDao rouletteDao;

	@PostMapping
	public Roulette save(@RequestBody Roulette roulette){
		return rouletteDao.save(roulette);
	}

	@GetMapping
	public List<Roulette> listRoulettes(){
		return rouletteDao.findAll();
	}

	@PostMapping("/open/{id}")
	public void openRoullete(@PathVariable int id){
		rouletteDao.openRoulette(id);
	}

	@PostMapping("/close/{id}")
	public void closeRoullete(@PathVariable int id){
		rouletteDao.closeRoulette(id);
	}

	@DeleteMapping("/{id}")
	public String deleteRoulette(@PathVariable int id){
		return rouletteDao.remove(id);
	}

	public static void main(String[] args) {
		SpringApplication.run(MasivRuletaApplication.class, args);
	}

}
