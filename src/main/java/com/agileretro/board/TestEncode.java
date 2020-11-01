package com.agileretro.board;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestEncode {

	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("admin"));
	}

}
