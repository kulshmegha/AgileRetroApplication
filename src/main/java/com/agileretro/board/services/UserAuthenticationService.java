package com.agileretro.board.services;

import com.agileretro.board.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component("userDetailsService")
public class UserAuthenticationService  implements UserDetailsService {
    @Autowired
    UserService userService;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByName(username);
        List<GrantedAuthority> grantedAuthorities =  new ArrayList<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(username,user.getPassword(),
                true,true,true,true,grantedAuthorities);
        return userDetails;
    }
}
