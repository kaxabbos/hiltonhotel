package com.hiltonhotel.repo;

import com.hiltonhotel.model.Cart;
import com.hiltonhotel.model.User;
import com.hiltonhotel.model.enums.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUser(User user);
    List<Cart> findAllByUserAndStatus(User user, CartStatus status);
    List<Cart> findAllByStatus(CartStatus status);
}
