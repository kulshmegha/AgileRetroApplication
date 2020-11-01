package com.agileretro.board.repository;

import com.agileretro.board.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public class UserRepositoryImpl implements UserRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    @Transactional
    public void refresh(User user) {
    	if(user != null)
    		entityManager.refresh(user);
    }
}
