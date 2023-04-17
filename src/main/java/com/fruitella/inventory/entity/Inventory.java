package com.fruitella.inventory.entity;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(schema = "inventory_system", name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "weight")
    private Double productWeight;

    @Column(name = "package")
    private Boolean productPackage;

    @Column(name = "fragile")
    private Boolean fragile;

    @Column(name = "price_usd")
    private Double priceUsd;

    @Column(name = "insert_product_date")
    @Timestamp
    private LocalDate insertProductToInventory;

}
