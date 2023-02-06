package com.syberry.server.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    private User user;

    @ManyToMany(targetEntity = Product.class, cascade = CascadeType.ALL)
    @JoinTable(name = "cart_products",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    private double totalPrice = 0;

    public Cart() {

    }

    public Cart(User user) {
        this.user = user;
    }
}
