package com.syberry.server.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Data
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Min(value = 0, message = "Price must be greater than zero")
    private Double price;

    @Column(length = 3000)
    private String title;

    @Column(length = 10000)
    private String description;

    @ManyToOne(targetEntity = Brand.class, cascade = CascadeType.ALL)
    private Brand brand;

    @ManyToOne(targetEntity = Type.class, cascade = CascadeType.ALL)
    private Type type;
}
