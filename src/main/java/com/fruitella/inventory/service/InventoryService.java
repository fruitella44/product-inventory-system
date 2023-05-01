package com.fruitella.inventory.service;

import com.fruitella.inventory.entity.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface InventoryService {
    Page<Inventory> getListOfItemsPerPage(Pageable pageable);
    Inventory addNewItem(Inventory inventory);
    Inventory findItemById(Long itemId);
    Inventory updateExistedItem(Inventory inventory);
    void deleteExistedItemById(Long itemId);
}
