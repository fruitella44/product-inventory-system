package com.fruitella.inventory.controller;

import com.fruitella.inventory.entity.Inventory;
import com.fruitella.inventory.service.InventoryService;;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InventoryRestController {

    private final InventoryService inventoryService;

    public InventoryRestController(InventoryService inventoryService) {
        super();
        this.inventoryService = inventoryService;
    }

    @GetMapping("/items")
    public List<Inventory> getAllInventoryItems() {
        return inventoryService.getAllItems();
    }

    @GetMapping("/items/new")
    public Inventory addNewInventoryItem(Inventory inventory) {
        return inventoryService.addNewItem(inventory);
    }
}
