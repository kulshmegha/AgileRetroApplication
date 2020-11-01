package com.agileretro.board;

import com.agileretro.board.domain.User;
import com.agileretro.board.repository.UserRepository;
import com.agileretro.board.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private BCryptPasswordEncoder pwdEncode;

    private User user;
    private UserService service;

    @BeforeEach
    public void setUp(){
        initMocks(this);
        service = new UserService(userRepository,pwdEncode);
        user = User.builder()
                .id(1)
                .username("test_name")
                .fullname("test_fullName")
                .password("test_password")
                .role("Admin")
                .build();

        when(userRepository.save(any())).thenReturn(user);
        when(userRepository.findUserByUsername("test_name")).thenReturn(user);
    }
   @Test
    public void testFindUserByUserName(){
        String userName = "test_name";
        User finalUser = service.findUserByName(userName);
        assertEquals(userName ,finalUser.getUsername());
   }
   @Test
    public void testAddUser(){
        User finalUser = service.save(user);
        assertEquals(user.getFullname(),finalUser.getFullname());
        assertEquals(user.getUsername(),finalUser.getUsername());
        assertEquals(user.getRole(),finalUser.getRole());
   }

}
