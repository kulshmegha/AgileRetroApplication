package com.agileretro.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agileretro.board.domain.Retro;

@Repository
public interface RetroRepository extends JpaRepository<Retro,Integer> {
	
	public List<Retro> findByRetroBoard(int boardid);
	
    
}
