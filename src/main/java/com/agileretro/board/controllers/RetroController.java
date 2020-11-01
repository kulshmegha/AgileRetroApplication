package com.agileretro.board.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.agileretro.board.domain.Retro;
import com.agileretro.board.services.RetroService;

public class RetroController {
	@Autowired
	RetroService service;
	
	
	@PostMapping(value = "/retros", produces = "application/json")
	public ResponseEntity<Retro> save(@RequestBody Retro retro){
		Retro addedRetro = service.save(retro);
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
               .path("/retros/{retroid}").buildAndExpand(addedRetro.getRetroid()).toUri();
		System.out.print("Retro Board Path : " + location);
		return ResponseEntity.created(location).body(addedRetro);
		
	}
	
	@GetMapping(value = "/retros", produces = "application/json")
	public ResponseEntity<Retro[]> all(){
		List<Retro>  retros =  service.findAllRetros();
		Retro[] arrayOfRetros =  retros.toArray(new Retro[retros.size()]);
		return ResponseEntity.ok(arrayOfRetros);
	}
	
	@GetMapping(value = "/retros/{id}", produces = "application/json")
	public ResponseEntity<Retro> getRetro(@PathVariable int id){
		Optional<Retro> retro =  service.findRetroById(id);
		if(!retro.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(retro.get());
	}
	
	
	@GetMapping(value = "/retros/board/{boardid}", produces = "application/json")
	public ResponseEntity<Retro[]> getRetrosForRetroBoard(@PathVariable int boardid){
		List<Retro> retros = service.findRetroByBoard(boardid);
		Retro[] arrayOfRetros =  retros.toArray(new Retro[retros.size()]);
		return ResponseEntity.ok(arrayOfRetros);
	}
	
	@DeleteMapping(value = "/retro/{id}", produces = "application/json")
	public ResponseEntity<Long> delete(@PathVariable int id){
		Optional<Retro> retro =  service.findRetroById(id);
		if(retro.isPresent()) {
			service.delete(retro.get());
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}

}
