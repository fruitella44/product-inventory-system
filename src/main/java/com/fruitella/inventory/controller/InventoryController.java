package com.fruitella.inventory.controller;

import com.fruitella.inventory.entity.Inventory;
import com.fruitella.inventory.service.InventoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        super();
        this.inventoryService = inventoryService;
    }

    @GetMapping("/items")
    public String listOfItemsInventory(Model model ) {
        model.addAttribute("items", inventoryService.getAllItems());
        return "items";
    }

    @GetMapping("/itemsPerPage")
    public String listOfItemsInventoryPerPage(Model model,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size) {
        Page<Inventory> inventoryPages = inventoryService.getListOfItemsPerPage(PageRequest.of(page, size));
        model.addAttribute("items", inventoryPages);
        return "items";
    }


    @GetMapping("/items/new")
    public String addNewItemForm(Model model) {
        Inventory inventory = new Inventory();
        model.addAttribute("item", inventory);
        return "create_item";
    }

    @PostMapping("/items")
    public String addNewItemInventory(@ModelAttribute("item") Inventory inventory) {
        inventoryService.addNewItem(inventory);
        return "redirect:/items";
    }

    @GetMapping("/items/edit/{id}")
    public String editItemForm(@PathVariable Long id, Model model) {
        model.addAttribute("item", inventoryService.findItemById(id));
        return "edit_item";
    }

    @PostMapping("/items/{id}")
    public String updateItemInventory(@PathVariable Long id,
                                @ModelAttribute("item") Inventory inventory,
                                Model model) {
        Inventory existedItem = inventoryService.findItemById(id);
        existedItem.setId(id);
        existedItem.setProductName(inventory.getProductName());
        existedItem.setProductType(inventory.getProductType());
        existedItem.setProductWeight(inventory.getProductWeight());
        existedItem.setProductPackage(inventory.getProductPackage());
        existedItem.setFragile(inventory.getFragile());
        existedItem.setInsertProductToInventory(inventory.getInsertProductToInventory());
        existedItem.setPriceUsd(inventory.getPriceUsd());

        inventoryService.updateExistedItem(existedItem);
        return "redirect:/items";
    }

    @GetMapping("/items/{id}")
    public String deleteItemInventory(@PathVariable Long id) {
        inventoryService.deleteExistedItemById(id);
        return "redirect:/items";
    }
}
