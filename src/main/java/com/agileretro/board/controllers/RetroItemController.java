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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.agileretro.board.domain.RetroItem;
import com.agileretro.board.services.RetroItemService;

@RestController
public class RetroItemController {
	@Autowired
	RetroItemService service;
	
	
	@PostMapping(value = "/retroitems", produces = "application/json")
	public ResponseEntity<RetroItem> save(@RequestBody RetroItem item){
		RetroItem addedItem = service.save(item);
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
               .path("/retroitems/{id}").buildAndExpand(addedItem.getRetroid()).toUri();
		System.out.print("Retro Item Path : " + location);
		return ResponseEntity.created(location).body(addedItem);
		
	}
	
	@GetMapping(value = "/retroitems", produces = "application/json")
	public ResponseEntity<RetroItem[]> all(){
		List<RetroItem>  items =  service.findAllRetros();
		RetroItem[] arrayOfItems =  items.toArray(new RetroItem[items.size()]);
		return ResponseEntity.ok(arrayOfItems);
	}
	
	@GetMapping(value = "/retroitems/{id}", produces = "application/json")
	public ResponseEntity<RetroItem> getRetroBoard(@PathVariable int id){
		Optional<RetroItem> item =  service.findRetroItemById(id);
		if(!item.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(item.get());
	}
	
	
	@DeleteMapping(value = "/retroitems{id}", produces = "application/json")
	public ResponseEntity<Long> delete(@PathVariable int id){
		Optional<RetroItem> item =  service.findRetroItemById(id);
		if(item.isPresent()) {
			service.delete(item.get());
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}

}
