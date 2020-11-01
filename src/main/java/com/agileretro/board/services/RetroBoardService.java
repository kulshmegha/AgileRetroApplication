package com.agileretro.board.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agileretro.board.domain.BoardUser;
import com.agileretro.board.domain.RetroBoard;
import com.agileretro.board.repository.RetroBoardRepository;

@Service
public class RetroBoardService {
	@Autowired
    RetroBoardRepository repository;
	
	public RetroBoard save(RetroBoard board) {
		board.setCreationdate(new Date(System.currentTimeMillis()));
		return repository.saveAndFlush(board);
	}
	
	public List<RetroBoard> findAllBoards(){
		return repository.findAll();
	}

	public Optional<RetroBoard> findRetroBoardById(int id) {
		return repository.findById(id);
	}

	public void delete(RetroBoard board) {
		repository.delete(board);
	}

	public List<BoardUser> findRetroBoardByUserId(String username) {
		return repository.join(username);
	}

}
