package com.fruitella.inventory.service;

import com.fruitella.inventory.entity.Inventory;

import java.util.List;

public interface InventoryService {
    List<Inventory> getAll();
    Inventory addNewItem(Inventory inventory);
    Inventory findItemById(Long itemId);
    Inventory updateExistedItem(Inventory inventory);
    void deleteExistedItemById(Long itemId);
}
