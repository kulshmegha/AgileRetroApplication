package com.agileretro.board.domain;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "retro_board")
@Data
@Entity
public class RetroBoard implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy  = GenerationType.AUTO)
    @Column(name = "board_id")
    private int id;
	
    @Column(name =  "board_name")
    private String boardname;
    
    @Column(name =  "creation_date")
    @CreatedDate
    private Date creationdate;
    
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "board_id")
//    private Set<Retro> retros = new HashSet<>();
    
    @OneToMany
    @JoinTable(name = "board_users",
            joinColumns = {@JoinColumn(name = "board_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> users = new ArrayList<>(); 
}
