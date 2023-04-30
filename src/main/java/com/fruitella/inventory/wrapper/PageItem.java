package com.fruitella.inventory.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PageItem {
    private int number;
    private boolean current;
}
