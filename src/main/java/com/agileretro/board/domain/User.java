package com.agileretro.board.domain;


import lombok.*;

import java.io.Serializable;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "user")
@Entity
public class User implements Serializable {
    
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;
    @Column(name =  "user_name")
    private String username;
    @Column(name="full_name")
    private String fullname;
    @Column(name = "password")
    private String password;
    @Column(name = "user_role")
    private String role;
    
    @Transient
    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = null;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
