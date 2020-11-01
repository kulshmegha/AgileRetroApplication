package com.agileretro.board.repository;

import com.agileretro.board.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>, UserRepositoryCustom {
    public User findUserByUsername(String username);
}
