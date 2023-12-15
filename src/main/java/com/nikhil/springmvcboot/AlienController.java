package com.nikhil.springmvcboot;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nikhil.springmvcboot.model.Alien;

@RestController
public class AlienController {
	
	@Autowired
	AlienRepo repo;
	
	
	@GetMapping(path="aliens",produces={"application/xml"})
	public List<Alien> getAliens() {
		System.out.println("Fetching Aliens");
		List<Alien> aliens = repo.findAll();
		return aliens;
	}
	
	@GetMapping("alien/{aid}")
	public Alien getAlien(@PathVariable("aid") int aid) {
		Alien alien = repo.findById(aid).orElse(new Alien(0, ""));
		return alien;
	}
	
	@PostMapping(path="alien",consumes= {MediaType.APPLICATION_JSON_VALUE})
	public Alien addAlien(@RequestBody Alien alien) {
		repo.save(alien);
		return alien;
	}
}
