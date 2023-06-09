package com.fruitella.inventory.controller;

import com.fruitella.inventory.entity.Inventory;
import com.fruitella.inventory.service.InventoryService;
import org.hamcrest.Matchers;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class InventoryControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryService inventoryService;

    @Test
    public void testControllerListItemsOfInventory() throws Exception {
        List<Inventory> inventoryList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            inventoryList.add(new Inventory());
        }

        Page<Inventory> page = new PageImpl<>(inventoryList);
        Mockito.when(inventoryService.getListOfItemsPerPage(PageRequest.of(0, 10)))
                .thenReturn(page);

        mockMvc.perform(get("/items").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(view().name("items"))
                .andExpect(model().attributeExists("items"))
                .andExpect(model().attribute("items", page));
    }

    @Test
    public void testControllerEditInventoryItemForm() throws Exception {
        Inventory inventory = Inventory.builder()
                .id(999L)
                .productName("Focusrite-solo")
                .productType("Soundcard")
                .productPackage(false)
                .fragile(false)
                .productWeight(0.9)
                .insertProductToInventory("02-02-2023")
                .priceUsd(150.50)
                .build();
        when(inventoryService.findItemById(anyLong())).thenReturn(inventory);

        mockMvc.perform(get("/items/edit/{id}", 999L).accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(view().name("edit_item"))
                .andExpect(model().attributeExists("item"))
                .andExpect(model().attribute("item", Matchers.notNullValue()))
                .andExpect(model().attribute("item", Matchers.samePropertyValuesAs(inventory)));
    }

    @Test
    public void testControllerUpdateExistedItem() throws Exception {
        Long id = 999L;
        Inventory inventory = new Inventory();
        inventory.setProductName("Behringer V8");
        inventory.setProductType("Soundcard");

        Inventory existedItem = new Inventory();
        when(inventoryService.findItemById(id)).thenReturn(existedItem);

        mockMvc.perform(post("/items/{id}", id)
                        .flashAttr("item", inventory))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/items"));

        verify(inventoryService).updateExistedItem(existedItem);
    }

    @Test
    public void testControllerDeleteItemFromInventory() throws Exception {
        Long inventoryItemId = 999L;

        mockMvc.perform(get("/items/{id}", inventoryItemId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/items"));

        verify(inventoryService, times(1)).deleteExistedItemById(inventoryItemId);
    }

}
