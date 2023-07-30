package com.syberry.server.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "type")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Type() {

    }

    public Type(String name) {
        this.name = name;
    }
}
