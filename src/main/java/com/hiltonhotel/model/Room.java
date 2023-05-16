package com.hiltonhotel.model;

import com.hiltonhotel.model.enums.Category;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Room {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    private int price;
    private int quantity;
    private String description;
    private String img;
    private String floor;
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cart> carts;
    @OneToOne(cascade = CascadeType.ALL)
    private Stat stat;

    public Room(String name, Category category, int price, int quantity, String description, String floor) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.floor = floor;
        this.carts = new ArrayList<>();
    }

    public void addCart(Cart cart) {
        carts.add(cart);
        cart.setRoom(this);
    }

    public void removeCart(Cart cart) {
        carts.remove(cart);
        cart.setRoom(null);
    }
}
