package com.agileretro.board.domain;


import lombok.*;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "retroS")
@Data
@EqualsAndHashCode
@Entity
public class RetroItem implements Serializable{
   
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private int itemid;
    
    @Column(name = "retro_id")
     private int retroid;
    
    @CreatedDate
    @NonNull
    @Column(name =  "creation_date")
    private Date creationdate;
    
    @NonNull
    @Column(name = "item_type")
    private String type;
    
    @Column(name ="item_details")
    private String detail;
    
    @Column(name = "sequence_id" )
    private int sequence;
    
    @Column(name = "action_target_date")
    private Date actiontargetdate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "retro_id",insertable = false, updatable = false)
    private Retro retro;
    
}
