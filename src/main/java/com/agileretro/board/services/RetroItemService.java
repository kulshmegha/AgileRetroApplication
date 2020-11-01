package com.agileretro.board.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agileretro.board.domain.RetroItem;
import com.agileretro.board.repository.RetroItemRepository;

@Service
public class RetroItemService {
	@Autowired
    RetroItemRepository repository;
	
	public RetroItem save(RetroItem retroItem) {
		retroItem.setCreationdate(new Date(System.currentTimeMillis()));
		return repository.save(retroItem);
	}
	
	public List<RetroItem> findAllRetros(){
		return repository.findAll();
	}

	public Optional<RetroItem> findRetroItemById(int id) {
		return repository.findById(id);
	}

	public void delete(RetroItem retroItem) {
		repository.delete(retroItem);
	}
}
