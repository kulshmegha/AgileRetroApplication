package com.agileretro.board.repository;

import com.agileretro.board.domain.BoardUser;
import com.agileretro.board.domain.RetroBoard;
import com.agileretro.board.domain.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RetroBoardRepository extends JpaRepository<RetroBoard,Integer> {
	@Query("select new com.agileretro.board.domain.BoardUser(b.id,b.boardname,u.id,u.username) from RetroBoard b, User u where u.username = ?1")
	public List<BoardUser> join(String username);
    
}
