package com.agileretro.board.services;

import com.agileretro.board.domain.User;
import com.agileretro.board.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userRepository;
    BCryptPasswordEncoder pwdEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder pwdEncoder){
       this.userRepository =  userRepository;
       this.pwdEncoder = pwdEncoder;
    }

   public User findUserByName(String userName){
       User user = userRepository.findUserByUsername(userName);
       userRepository.refresh(user);
       return user;
   }

   public User save(User user){
        user.setPassword(pwdEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
   }
	
	public List<User> getUsers() {
		return userRepository.findAll();
	}
}
