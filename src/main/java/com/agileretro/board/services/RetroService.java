package com.agileretro.board.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agileretro.board.domain.Retro;
import com.agileretro.board.repository.RetroRepository;

@Service
public class RetroService {
	@Autowired
    RetroRepository repository;
	
	public Retro save(Retro retro) {
		retro.setCreationdate(new Date(System.currentTimeMillis()));
		return repository.saveAndFlush(retro);
	}
	
	public List<Retro> findAllRetros(){
		return repository.findAll();
	}

	public Optional<Retro> findRetroById(int id) {
		return repository.findById(id);
	}

	public void delete(Retro retro) {
		repository.delete(retro);
	}

	public List<Retro> findRetroByBoard(int boardid) {
		return repository.findByRetroBoard(boardid);
	}

}
