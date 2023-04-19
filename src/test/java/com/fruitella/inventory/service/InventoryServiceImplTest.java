package com.fruitella.inventory.service;

import com.fruitella.inventory.entity.Inventory;
import com.fruitella.inventory.repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
class InventoryServiceImplTest {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Test
    public void testGetAllItemsFromInventory() {
        List<String> productNames = inventoryService.getAllItems().stream()
                .map(Inventory :: getProductName)
                .collect(Collectors.toList());
        assertThat(productNames, hasItems("Playstation 4", "Iphone X", "Samsung Ultra Sharp", "D-Link DES-1008D"));
    }

    @Test
    public void testFindItemByIdFromInventory() {
        Inventory inventory = Inventory.builder()
                .productName("A4-Tech")
                .productType("Mouse")
                .productPackage(true)
                .fragile(true)
                .productWeight(0.250)
                .insertProductToInventory("01-01-2023")
                .priceUsd(99.99)
                .build();
        inventoryRepository.save(inventory);

        Inventory foundItem = inventoryService.findItemById(inventory.getId());
        assertNotNull(foundItem);
        assertEquals(inventory.getId(), foundItem.getId());
        assertEquals(inventory.getProductName(), foundItem.getProductName());
        assertEquals(inventory.getProductType(), foundItem.getProductType());
        assertEquals(inventory.getProductPackage(), foundItem.getProductPackage());
        assertEquals(inventory.getFragile(), foundItem.getFragile());
        assertEquals(inventory.getProductWeight(), foundItem.getProductWeight());
        assertEquals(inventory.getInsertProductToInventory(), foundItem.getInsertProductToInventory());
        assertEquals(inventory.getPriceUsd(), foundItem.getPriceUsd());
    }

    @Test
    public void testAddNewItemToInventory() {
        Inventory newItemInventory = Inventory.builder()
                .productName("A4-Tech")
                .productType("Mouse")
                .productPackage(true)
                .fragile(true)
                .productWeight(0.250)
                .insertProductToInventory("01-01-2023")
                .priceUsd(99.99)
                .build();
        inventoryRepository.save(newItemInventory);

        Inventory saveItem = inventoryService.addNewItem(newItemInventory);
        Inventory foundItem = inventoryService.findItemById(saveItem.getId());
        assertNotNull(foundItem);
    }


    @Test
    public void testUpdateExistedItemFromInventory() {
        Inventory newItemInventory = Inventory.builder()
                .productName("A4-Tech")
                .productType("Mouse")
                .productPackage(true)
                .fragile(true)
                .productWeight(0.250)
                .insertProductToInventory("01-01-2023")
                .priceUsd(99.99)
                .build();
        inventoryRepository.save(newItemInventory);

        Inventory existedItem = Inventory.builder()
                .productName("Focusrite-solo")
                .productType("Soundcard")
                .productPackage(false)
                .fragile(false)
                .productWeight(0.9)
                .insertProductToInventory("02-02-2023")
                .priceUsd(150.50)
                .build();
        inventoryService.updateExistedItem(existedItem);

        Inventory updateExistedItem = inventoryRepository.findById(existedItem.getId()).orElse(null);
        assertNotNull(updateExistedItem);
        assertEquals("Focusrite-solo", updateExistedItem.getProductName());
        assertEquals("Soundcard", updateExistedItem.getProductType());
        assertEquals(false, updateExistedItem.getProductPackage());
        assertEquals(false, updateExistedItem.getFragile());
        assertEquals(0.9, updateExistedItem.getProductWeight());
        assertEquals("02-02-2023", updateExistedItem.getInsertProductToInventory());
        assertEquals(150.50, updateExistedItem.getPriceUsd());
    }

    @Test
    public void testDeleteExistedItemFromInventory() {
        Inventory newItemInventory = Inventory.builder()
                .productName("A4-Tech")
                .productType("Mouse")
                .productPackage(true)
                .fragile(true)
                .productWeight(0.250)
                .insertProductToInventory("01-01-2023")
                .priceUsd(99.99)
                .build();
        inventoryRepository.save(newItemInventory);

        Inventory foundItem = inventoryService.findItemById(newItemInventory.getId());
        assertNotNull(foundItem);

        inventoryService.deleteExistedItemById(foundItem.getId());
        Inventory deletedItem = inventoryRepository.findById(foundItem.getId()).orElse(null);
        assertNull(deletedItem);
    }

}