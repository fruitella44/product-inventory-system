package com.fruitella.inventory.repository;

import com.fruitella.inventory.entity.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    @Query("SELECT items FROM Inventory items")
    Page<Inventory> listOfItemsPerPage(Pageable pageable);
}
