package com.scoreboard.match.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@Table(name = "team")
@NoArgsConstructor
@AllArgsConstructor
public class TeamEntity {
    @Id
    private String name;
    private String description;
    private String selector;
    private String country;

}
