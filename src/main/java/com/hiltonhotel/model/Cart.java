package com.hiltonhotel.model;

import com.hiltonhotel.model.enums.CartStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cart {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private int quantity;
    private String date;
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    @Enumerated(EnumType.STRING)
    private CartStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    public Cart(int quantity, String date, User user, Room room) {
        this.quantity = quantity;
        this.date = date;
        this.user = user;
        this.room = room;
        this.status = CartStatus.WAITING;
    }
}
