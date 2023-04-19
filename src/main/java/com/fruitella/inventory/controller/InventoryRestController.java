package com.fruitella.inventory.controller;

import com.fruitella.inventory.entity.Inventory;
import com.fruitella.inventory.service.InventoryService;;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Inventory>> getAllInventoryItems() {
        return ResponseEntity.ok().body(inventoryService.getAllItems());
    }


    @GetMapping("/items/{itemId}")
    public ResponseEntity<Inventory> getInventoryItemById(@PathVariable Long itemId) {
        return ResponseEntity.ok().body(inventoryService.findItemById(itemId));
    }

    @GetMapping("/items/new")
    public ResponseEntity<Inventory> addNewInventoryItem(Inventory inventory) {
        return ResponseEntity.ok().body(inventoryService.addNewItem(inventory));
    }

    @GetMapping("/items/edit/{itemId}")
    public ResponseEntity<Inventory> updateExistedInventoryItemById(@PathVariable Long itemId) {
        Inventory inventory = inventoryService.findItemById(itemId);
        return ResponseEntity.ok().body(inventoryService.updateExistedItem(inventory));
    }

    @GetMapping("/items/delete/{itemId}")
    public void deleteExistedInventoryItemById(@PathVariable Long itemId) {
         inventoryService.deleteExistedItemById(itemId);
    }
}
