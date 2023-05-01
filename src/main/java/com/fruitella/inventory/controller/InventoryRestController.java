package com.fruitella.inventory.controller;

import com.fruitella.inventory.entity.Inventory;
import com.fruitella.inventory.service.InventoryService;
import lombok.extern.log4j.Log4j2;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
@Log4j2
public class InventoryRestController {
    private final InventoryService inventoryService;

    public InventoryRestController(InventoryService inventoryService) {
        super();
        this.inventoryService = inventoryService;
    }

    @GetMapping("/items")
    public ResponseEntity<Page<Inventory>> getAllInventoryItems() {
        log.debug("Return presentation level with json. Content[AllItems]");
        return ResponseEntity.ok().body(inventoryService.getListOfItemsPerPage(PageRequest.of(0, 10)));
    }


    @GetMapping("/items/{itemId}")
    public ResponseEntity<Inventory> getInventoryItemById(@PathVariable Long itemId) {
        log.debug("Return presentation level with json. Content[getItem with id: " + itemId + "]");
        return ResponseEntity.ok().body(inventoryService.findItemById(itemId));
    }

    @PostMapping("/items/new")
    public ResponseEntity<Inventory> addNewInventoryItem(Inventory inventory) {
        log.debug("Return presentation level with json. Content[create new]");
        return ResponseEntity.ok().body(inventoryService.addNewItem(inventory));
    }

    @PostMapping("/items/edit/{itemId}")
    public ResponseEntity<Inventory> updateExistedInventoryItemById(@PathVariable Long itemId) {
        Inventory inventory = inventoryService.findItemById(itemId);
        log.debug("Return presentation level with json. Content[findItemById, current id: + " + itemId + "]");
        return ResponseEntity.ok().body(inventoryService.updateExistedItem(inventory));
    }

    @PostMapping("/items/delete/{itemId}")
    public void deleteExistedInventoryItemById(@PathVariable Long itemId) {
        log.debug("Return presentation level with json. Content[deleteItemById, current id: + " + itemId + "]");
         inventoryService.deleteExistedItemById(itemId);
    }
}
