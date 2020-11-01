package com.agileretro.board.domain;

import java.io.Serializable;

public class BoardUser implements  Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int boardid;
	private int userid;
	private String boardname;
	private String username;
	
	
	
	public BoardUser(int boardid,String boardname, int userid,String username) {
		super();
		this.boardid = boardid;
		this.userid = userid;
		this.boardname = boardname;
		this.username = username;
	}
	public int getBoardid() {
		return boardid;
	}
	public void setBoardid(int boardid) {
		this.boardid = boardid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getBoardname() {
		return boardname;
	}
	public void setBoardname(String boardname) {
		this.boardname = boardname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
  
}
