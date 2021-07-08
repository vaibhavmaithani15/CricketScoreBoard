package com.scoreboard.match.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Data
//@Builder
//@Table(name = "out_type")
//@NoArgsConstructor
//@AllArgsConstructor
public class OutType {
    private int outTypeId;
    private String type;
}

