package com.fruitella.inventory.controller;

import com.fruitella.inventory.entity.Inventory;
import com.fruitella.inventory.service.InventoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class InventoryRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryService inventoryService;


    @Test
    public void testRestControllerListItemsOfInventory() throws Exception {
        List<Inventory> inventoryList = new ArrayList<>();
        Inventory newItem = Inventory.builder()
                .id(1L)
                .productName("Samsung Ultra HD 8K")
                .productType("TV")
                .productWeight(35.8)
                .productPackage(true)
                .fragile(false)
                .priceUsd(4087.62)
                .insertProductToInventory("2023-04-17")
                .build();
        inventoryList.add(newItem);

        Page<Inventory> page = new PageImpl<>(inventoryList);
        Mockito.when(inventoryService.getListOfItemsPerPage(PageRequest.of(0, 10)))
                .thenReturn(page);

        mockMvc.perform(get("/api/items").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id", is(1)))
                .andExpect(jsonPath("$.content[0].productName", is("Samsung Ultra HD 8K")))
                .andExpect(jsonPath("$.content[0].productType", is("TV")))
                .andExpect(jsonPath("$.content[0].productWeight", is(35.8)))
                .andExpect(jsonPath("$.content[0].productPackage", is(true)))
                .andExpect(jsonPath("$.content[0].fragile", is(false)))
                .andExpect(jsonPath("$.content[0].priceUsd", is(4087.62)))
                .andExpect(jsonPath("$.content[0].insertProductToInventory", is("2023-04-17")))
                .andExpect(jsonPath("$.totalElements", is(1)))
                .andExpect(jsonPath("$.totalPages", is(1)))
                .andExpect(jsonPath("$.first", is(true)))
                .andExpect(jsonPath("$.last", is(true)))
                .andExpect(jsonPath("$.size", is(1)))
                .andExpect(jsonPath("$.number", is(0)));
    }

    @Test
    public void testRestControllerGetInventoryItemById() throws Exception {
        Inventory existedItem = Inventory.builder()
                .id(1L)
                .productName("Samsung Ultra HD 8K")
                .productType("TV")
                .productWeight(35.8)
                .productPackage(true)
                .fragile(false)
                .priceUsd(4087.62)
                .insertProductToInventory("2023-04-17")
                .build();

        Mockito.when(inventoryService.findItemById(1L)).thenReturn(existedItem);

        mockMvc.perform(get("/api/items/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.productName", is("Samsung Ultra HD 8K")))
                .andExpect(jsonPath("$.productType", is("TV")))
                .andExpect(jsonPath("$.productWeight", is(35.8)))
                .andExpect(jsonPath("$.productPackage", is(true)))
                .andExpect(jsonPath("$.fragile", is(false)))
                .andExpect(jsonPath("$.priceUsd", is(4087.62)))
                .andExpect(jsonPath("$.insertProductToInventory", is("2023-04-17")));
    }


    @Test
    public void testRestControllerAddNewInventoryItem() throws Exception {
        Inventory newItem = Inventory.builder()
                .id(999L)
                .productName("Samsung ULTRA SHARP")
                .productType("TV")
                .productWeight(35.8)
                .productPackage(true)
                .fragile(true)
                .priceUsd(4500.99)
                .insertProductToInventory("2023-01-05")
                .build();

        Mockito.when(inventoryService.addNewItem(Mockito.any(Inventory.class))).thenReturn(newItem);

        mockMvc.perform(post("/api/items/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ " +
                                "\"id\": 999, " +
                                "\"productName\": \"Samsung ULTRA SHARP\", " +
                                "\"productType\": \"TV\", " +
                                "\"productWeight\": 35.8, " +
                                "\"productPackage\": true, " +
                                "\"fragile\": true, " +
                                "\"priceUsd\": 4500.99, " +
                                "\"insertProductToInventory\": \"2023-01-05\" " +
                                "}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(999)))
                .andExpect(jsonPath("$.productName", is("Samsung ULTRA SHARP")))
                .andExpect(jsonPath("$.productType", is("TV")))
                .andExpect(jsonPath("$.productWeight", is(35.8)))
                .andExpect(jsonPath("$.productPackage", is(true)))
                .andExpect(jsonPath("$.fragile", is(true)))
                .andExpect(jsonPath("$.priceUsd", is(4500.99)))
                .andExpect(jsonPath("$.insertProductToInventory", is("2023-01-05")));
    }


    @Test
    public void testRestControllerDeleteExistedInventoryItemById() throws Exception {
        Long itemId = 1L;

        doNothing().when(inventoryService).deleteExistedItemById(itemId);
        mockMvc.perform(post("/api/items/delete/" + itemId))
                .andExpect(status().isOk());

        verify(inventoryService, times(1)).deleteExistedItemById(itemId);
    }

}
