package com.fruitella.inventory;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ProductInventorySystemApplicationTests {

    @Test
    void contextLoads() {
        ProductInventorySystemApplication.main(new String[] {});
    }

}
