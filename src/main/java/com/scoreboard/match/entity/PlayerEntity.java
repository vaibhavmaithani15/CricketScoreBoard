package com.scoreboard.match.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@Table(name = "player")
@NoArgsConstructor
@AllArgsConstructor
public class PlayerEntity {
    @Id
    @GeneratedValue( strategy=GenerationType.AUTO )
    private int playerId;
    private String name;
    private Date dob;
    private String country;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_name", referencedColumnName = "name")
    private TeamEntity teamEntity;
}
