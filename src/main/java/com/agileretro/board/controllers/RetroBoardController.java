package com.agileretro.board.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.agileretro.board.domain.BoardUser;
import com.agileretro.board.domain.RetroBoard;
import com.agileretro.board.domain.User;
import com.agileretro.board.services.RetroBoardService;
import com.agileretro.board.services.UserService;

@RestController
public class RetroBoardController {
	
	@Autowired
	RetroBoardService service;
	
	@PostMapping(value = "/retroboards", produces = "application/json")
	public ResponseEntity<RetroBoard> save(@RequestBody RetroBoard board){
		RetroBoard addedBoard = service.save(board);
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
               .path("/retroboards/{id}").buildAndExpand(addedBoard.getId()).toUri();
		System.out.print("Retro Board Path : " + location);
		return ResponseEntity.created(location).body(addedBoard);
		
	}
	
	@GetMapping(value = "/retroboards", produces = "application/json")
	public ResponseEntity<RetroBoard[]> all(){
		List<RetroBoard>  boards =  service.findAllBoards();
		RetroBoard[] arrayOfBoards =  boards.toArray(new RetroBoard[boards.size()]);
		return ResponseEntity.ok(arrayOfBoards);
	}
	
	@GetMapping(value = "/retroboards/{id}", produces = "application/json")
	public ResponseEntity<RetroBoard> getRetroBoard(@PathVariable int id){
		Optional<RetroBoard> board =  service.findRetroBoardById(id);
		if(!board.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		board.get().getUsers();
		return ResponseEntity.ok(board.get());
	}
	
	
	@GetMapping(value = "/retroboards/user/{username}", produces = "application/json")
	public ResponseEntity<BoardUser[]> getUserRetroBoards(@PathVariable String username){
		List<BoardUser>  boardUsers = service.findRetroBoardByUserId(username);
		BoardUser[] boardUserArray =  boardUsers.toArray(new BoardUser[boardUsers.size()]);
		return ResponseEntity.ok(boardUserArray);
	}
	
	@DeleteMapping(value = "/retroboards/{id}", produces = "application/json")
	public ResponseEntity<Long> delete(@PathVariable int id){
		Optional<RetroBoard> board =  service.findRetroBoardById(id);
		if(board.isPresent()) {
			service.delete(board.get());
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
}
