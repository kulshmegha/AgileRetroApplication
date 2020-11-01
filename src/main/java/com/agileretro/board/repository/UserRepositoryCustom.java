package com.agileretro.board.repository;

import com.agileretro.board.domain.User;

public interface UserRepositoryCustom {
    void refresh(User user);
}
