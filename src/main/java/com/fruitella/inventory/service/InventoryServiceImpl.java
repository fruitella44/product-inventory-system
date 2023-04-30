package com.fruitella.inventory.service;

import com.fruitella.inventory.entity.Inventory;
import com.fruitella.inventory.repository.InventoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        super();
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<Inventory> getAllItems() {
        return inventoryRepository.findAll();
    }

    @Override
    public Page<Inventory> getListOfItemsPerPage(Pageable pageable) {
        return inventoryRepository.listOfItemsPerPage(pageable);
    }

    @Override
    public Inventory addNewItem(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory findItemById(Long itemId) {
        return inventoryRepository.findById(itemId).get();
    }

    @Override
    public Inventory updateExistedItem(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public void deleteExistedItemById(Long itemId) {
        inventoryRepository.deleteById(itemId);
    }
}
