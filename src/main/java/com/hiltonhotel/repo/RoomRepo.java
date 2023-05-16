package com.hiltonhotel.repo;

import com.hiltonhotel.model.Room;
import com.hiltonhotel.model.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {
    List<Room> findAllByCategory(Category category);
    List<Room> findAllByCategoryAndNameContaining(Category category, String name);
}
