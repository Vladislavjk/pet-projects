package com.syberry.server.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "wishlist")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    private User user;

    @ManyToMany(targetEntity = Product.class, cascade = CascadeType.ALL)
    @JoinTable(name = "wishlist_products",
            joinColumns = @JoinColumn(name = "wishlist_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    public Wishlist() {

    }

    public Wishlist(User user) {
        this.user = user;
    }
}
