package com.fruitella.inventory.service;

import com.fruitella.inventory.entity.Inventory;
import com.fruitella.inventory.logger.LogDeplucator;
import com.fruitella.inventory.repository.InventoryRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@Log4j2
public class InventoryServiceImpl implements InventoryService {
    private static final LogDeplucator deplucator = new LogDeplucator();
    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        super();
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Page<Inventory> getListOfItemsPerPage(Pageable pageable) {
        if (!deplucator.isDuplicate(log.getName())) {
            log.debug("Call JPA Repository with Interface Pageable");
        }
        return inventoryRepository.listOfItemsPerPage(pageable);
    }

    @Override
    public Inventory addNewItem(Inventory inventory) {
        if (!deplucator.isDuplicate(log.getName())) {
            log.debug("Call method [save] from JPA Repository");
        }
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory findItemById(Long itemId) {
        if (!deplucator.isDuplicate(log.getName())) {
            log.debug("Call method [findById] from JPA Repository");
        }
        return inventoryRepository.findById(itemId).get();
    }

    @Override
    public Inventory updateExistedItem(Inventory inventory) {
        if (!deplucator.isDuplicate(log.getName())) {
            log.debug("Call method [save] from JPA Repository");
        }
        return inventoryRepository.save(inventory);
    }

    @Override
    public void deleteExistedItemById(Long itemId) {
        if (!deplucator.isDuplicate(log.getName())) {
            log.debug("Call method [deleteById] from JPA Repository");
        }
        inventoryRepository.deleteById(itemId);
    }
}
