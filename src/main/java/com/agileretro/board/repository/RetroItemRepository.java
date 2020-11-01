package com.agileretro.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agileretro.board.domain.RetroItem;

@Repository
public interface RetroItemRepository extends JpaRepository<RetroItem,Integer> {
	
	//public List<Retro> findByRetroBoard(int boardid);
	
    
}
