package com.fruitella.inventory.DTO;

import com.fruitella.inventory.entity.Inventory;
import lombok.*;

import java.awt.print.Pageable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PageDTO {
    private List<Inventory> content;
    private int totalPages;
    private long totalElements;
    private int pageSize;
    private int pageNumber;

}
